/***************************************************************************************************
 * Copyright (c) 2007 DevZuz, Inc. (AKA Simula Labs, Inc.) All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 **************************************************************************************************/
package org.devzuz.q.maven.jdt.core.builder;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.maven.model.Resource;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.devzuz.q.maven.embedder.IMavenProject;
import org.devzuz.q.maven.embedder.MavenCoreActivator;
import org.devzuz.q.maven.embedder.MavenExecutionParameter;
import org.devzuz.q.maven.embedder.MavenManager;
import org.devzuz.q.maven.jdt.core.MavenJdtCoreActivator;
import org.devzuz.q.maven.jdt.core.classpath.container.UpdateClasspathJob;
import org.devzuz.q.maven.jdt.core.internal.TraceOption;
import org.devzuz.q.maven.jdt.core.properties.MavenPropertyManager;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * Maven builder that will update the classpath container when pom changes
 * 
 * @author <a href="mailto:carlos@apache.org">Carlos Sanchez</a>
 */
public class MavenIncrementalBuilder
    extends IncrementalProjectBuilder
{
    /**
     * TODO Document
     * 
     * @author amuino
     */
    private final class DeltaVisitor
        implements IResourceDeltaVisitor
    {
        private final String goal;

        private final IMavenProject mavenProject;

        private final Resource mavenResource;

        private boolean matchedResource;

        private final List<String> includes;

        private final List<String> excludes;

        /**
         * @param goal
         * @param mavenProject
         * @param mavenResource
         */
        public DeltaVisitor( String goal, IMavenProject mavenProject, Resource mavenResource )
        {
            this.goal = goal;
            this.mavenProject = mavenProject;
            this.mavenResource = mavenResource;
            // Initialize include/exclude defaults.
            this.includes =
                ( mavenResource.getIncludes().isEmpty() ) ? Collections.singletonList( "**/**" )
                                : mavenResource.getIncludes();
            this.excludes = mavenResource.getExcludes();
        }

        public boolean visit( IResourceDelta delta )
            throws CoreException
        {
            if ( delta.getResource().getType() == IResource.FILE )
            {
                // We only process the delta if it's a file
                String deltaPath = delta.getResource().getProjectRelativePath().toString();
                // If it matches any includes, and no excludes
                if ( matchesAnyPattern( deltaPath, includes ) && !matchesAnyPattern( deltaPath, excludes ) )
                {
                    // If the file was removed, delete it from the target
                    // directory.
                    if ( delta.getKind() == IResourceDelta.REMOVED )
                    {
                        processFileDelete( delta );
                    }
                    else
                    {
                        // Otherwise, list this file as being a match.
                        matchedResource = true;
                    }
                }
            }
            // process all changed resources (so we can delete generated files for deleted source files)
            return true;
        }

        /**
         * Returns true if any resource has been modified, matched by the inclusion patterns and not matched by the
         * exclusion patterns.
         * 
         * @return
         */
        public boolean isMatched()
        {
            return matchedResource;
        }

        /**
         * Handles deleting the file in the output folder.
         * 
         * @param delta the delta describing the delete.
         * @throws CoreException if an error occurs deleting the resource in the output folder.
         */
        private void processFileDelete( IResourceDelta delta )
            throws CoreException
        {
            IPath resourcePath = delta.getResource().getProjectRelativePath();
            IPath resourceRoot = new Path( mavenResource.getDirectory() );
            if ( resourceRoot.isAbsolute() )
            {
                resourceRoot =
                    resourceRoot.removeFirstSegments( getProject().getLocation().segmentCount() ).makeRelative();
            }
            resourcePath = resourcePath.removeFirstSegments( resourceRoot.segments().length );
            String buildOutputDir = null;
            if ( goal.equals( TEST_RESOURCES_GOAL ) )
            {
                buildOutputDir = mavenProject.getBuildTestOutputDirectory() + "/";
            }
            else
            {
                buildOutputDir = mavenProject.getBuildOutputDirectory() + "/";
            }
            IPath targetResourcePath = new Path( buildOutputDir );
            if ( null != mavenResource.getTargetPath() )
            {
                targetResourcePath = targetResourcePath.append( mavenResource.getTargetPath() );
            }
            targetResourcePath = targetResourcePath.append( resourcePath );
            if ( targetResourcePath.isAbsolute() )
            {
                targetResourcePath =
                    targetResourcePath.removeFirstSegments( getProject().getLocation().segmentCount() ).makeRelative();
            }
            IFile targetFile = getProject().getFile( targetResourcePath );
            if ( targetFile.exists() )
            {
                MavenJdtCoreActivator.trace( TraceOption.MAVEN_INCREMENTAL_BUILDER, "Deleting ", targetFile );
                targetFile.delete( true, new NullProgressMonitor() );
            }
        }

        /**
         * Checks if the given path matches any of the specified patterns.
         * 
         * @param resourcePath the path to check.
         * @param patterns the path patterns (ant style).
         * @return <code>true</code> if the path is matched by at least one of the patterns.
         */
        private boolean matchesAnyPattern( String resourcePath, List<String> patterns )
        {
            for ( String pattern : patterns )
            {
                boolean matches = SelectorUtils.matchPath( pattern, resourcePath );
                if ( matches )
                {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Helper class to manage the data passed to different methods of the builder. This class acts as a register and
     * intentionally has no accessor methods.
     * 
     * @author amuino
     */
    private class BuildStatus
    {

        public boolean resourcesRefreshed = false;

        public boolean testResourcesRefreshed = false;

        public IMavenProject mavenProject = null;

        public IResourceDelta delta = null;

        public IProgressMonitor monitor = null;
    }

    /**
     * 
     */
    public static final String TEST_RESOURCES_GOAL = "process-test-resources";

    /**
     * 
     */
    public static final String RESOURCES_GOAL = "process-resources";

    public static final String MAVEN_INCREMENTAL_BUILDER_ID =
        MavenJdtCoreActivator.PLUGIN_ID + ".mavenIncrementalBuilder"; //$NON-NLS-1$

    private static final Path POM_PATH = new Path( IMavenProject.POM_FILENAME );

    private IMavenProject lastGoodProject = null;

    @Override
    protected IProject[] build( int kind, Map args, IProgressMonitor monitor )
        throws CoreException
    {
        BuildStatus status = new BuildStatus();
        status.monitor = monitor;
        // The project as cached before the change.
        status.mavenProject = lastGoodProject;

        if ( ( kind == INCREMENTAL_BUILD ) || ( kind == AUTO_BUILD ) )
        {
            status.delta = getDelta( getProject() );

            // Check if the project's POM was updated.
            IResourceDelta member = status.delta.findMember( POM_PATH );
            if ( member != null )
            {
                handlePom( status, member );
                if ( null == status.mavenProject )
                {
                    // The pom.xml does not parse, do not continue
                    return null;
                }
            }
            else if ( null == lastGoodProject )
            {
                // No previous build and the pom was not modified --> Current project is good
                status.mavenProject = getMavenProject();
            }

            // If the change was to a file listed in <filters>, pom.xml parses and at least one resource has not been
            // synced we need to re-process the resources.
            if ( !status.resourcesRefreshed || !status.testResourcesRefreshed )
            {
                handleFilterFiles( status );
            }

            // If the change was to a resource file, pom.xml parses and at least on resource has not been synced we
            // need to process the resources.
            if ( !status.resourcesRefreshed || !status.testResourcesRefreshed )
            {
                handleAllResources( status );
            }
        }
        else
        {
            // full build
            onPomChange( status );
            // get the maven project after refreshing the pom so it is updated
            status.mavenProject = getMavenProject();
            if ( status.mavenProject != null )
            {
                onResourcesChange( status );
            }
        }
        if ( status.mavenProject != null )
        {
            lastGoodProject = status.mavenProject;
        }
        return null;
    }

    /**
     * @param status
     * @param member
     * @return
     * @throws CoreException
     */
    private void handlePom( BuildStatus status, IResourceDelta member )
        throws CoreException
    {
        // Process change event
        onPomChange( status );
        // Re-read project after processing change to the pom
        status.mavenProject = getMavenProject();
        if ( status.mavenProject == null )
        {
            // Non parseable after modification, do not try to run any goals
            MavenJdtCoreActivator.trace( TraceOption.MAVEN_INCREMENTAL_BUILDER,
                                         "pom.xml not parseable, skipping refreshing resources" );
            return;
        }
        else if ( lastGoodProject == null )
        {
            // No previous version to compare, process resources
            MavenJdtCoreActivator.trace( TraceOption.MAVEN_INCREMENTAL_BUILDER,
                                         "Previous version of pom.xml not parseable, forced refreshing resources" );
            onResourcesChange( status );
        }
        else
        {
            status.resourcesRefreshed =
                handleResourceConfig( status.mavenProject, lastGoodProject.getResources(),
                                      status.mavenProject.getResources(), RESOURCES_GOAL, status.monitor );
            status.testResourcesRefreshed =
                handleResourceConfig( status.mavenProject, lastGoodProject.getTestResources(),
                                      status.mavenProject.getTestResources(), TEST_RESOURCES_GOAL, status.monitor );
        }
    }

    /**
     * Checks for changes in the <filters> section of the pom and refreshes the affected resources when needed.
     * 
     * @param status the current build status.
     * @throws CoreException if there is an error refreshing the resources.
     */
    private void handleFilterFiles( BuildStatus status )
        throws CoreException
    {
        List<String> filters = status.mavenProject.getFilters();
        if ( filters != null )
        {
            for ( String filter : filters )
            {
                IPath filterPath = new Path( filter );
                if ( filterPath.isAbsolute() )
                {
                    filterPath =
                        filterPath.removeFirstSegments( getProject().getLocation().segmentCount() ).makeRelative();
                }
                IResourceDelta filterMember = status.delta.findMember( filterPath );
                if ( filterMember != null )
                {
                    // At least one filter file has changed, update all filtered resources left
                    onResourcesChange( status, false );
                    return;
                }
            }
        }
    }

    /**
     * Refreshes the resources with the given goal if the change detected in the pom requires it.
     * 
     * @param status the current build status.
     * @param oldProject the maven project before the change to the pom.
     * @return <code>true</code> if the resources have been refreshed as a result of the changes to the pom.
     * @throws CoreException if an error occurs while refreshing resources.
     */
    private boolean handleResourceConfig( IMavenProject mavenProject, List<Resource> oldResources,
                                          List<Resource> newResources, String goal, IProgressMonitor monitor )
        throws CoreException
    {
        boolean refreshRequired = false;
        boolean isResourceFiltered = isAnyResourceFiltered( newResources );
        if ( isResourceFiltered )
        {
            // Filtered resources can use almost any part of the pom. Refresh always.
            MavenJdtCoreActivator.trace( TraceOption.MAVEN_INCREMENTAL_BUILDER, "Filtering enabled: ", goal );
            refreshRequired = true;
        }
        else
        {
            // Non filtered resources, refresh if the resources configuration has changed
            refreshRequired = resourcesModified( newResources, oldResources );
            if ( refreshRequired )
            {
                MavenJdtCoreActivator.trace( TraceOption.MAVEN_INCREMENTAL_BUILDER,
                                             "Resources configuration changed: ", goal );
            }
        }
        if ( refreshRequired )
        {
            onResourcesChange( mavenProject, goal, monitor );
        }
        return refreshRequired;
    }

    /**
     * Utility method to compare two lists of resources, since {@link Resource} does not implement equals.
     * 
     * @param l1 a non-null list of resources.
     * @param l2 another non-null list of resources.
     * @return
     */
    private boolean resourcesModified( List<Resource> l1, List<Resource> l2 )
    {
        if ( l1 == null || l2 == null )
        {
            throw new IllegalArgumentException( "Resource lists can't be null for comparing." );
        }
        if ( l1.size() != l2.size() )
        {
            return false;
        }
        else
        {
            // same size, iterate in parallel
            Iterator<Resource> it1 = l1.iterator();
            Iterator<Resource> it2 = l2.iterator();
            boolean stillEquals = true;
            while ( stillEquals && it1.hasNext() )
            {
                Resource r1 = it1.next();
                Resource r2 = it2.next();
                stillEquals =
                    r1.getDirectory().equals( r2.getDirectory() )
                        && r1.getExcludes().equals( r2.getExcludes() )
                        && r1.getIncludes().equals( r2.getIncludes() )
                        && r1.getModelEncoding().equals( r2.getModelEncoding() )
                        && r1.isFiltering() == r2.isFiltering()
                        && ( ( r1.getTargetPath() == null && r2.getTargetPath() == null ) || r1.equals( r2.getTargetPath() ) );
            }
            return !stillEquals;
        }
    }

    /**
     * Checks if any of the resources in the list is being filtered.
     * 
     * @param resources the resources to check.
     * @return <code>true</code> if any of the passed resources is filtering, <code>false</code> otherwise.
     */
    private boolean isAnyResourceFiltered( List<Resource> resources )
    {
        for ( Resource r : resources )
        {
            if ( r.isFiltering() )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the maven project, handling exceptions
     * 
     * @return the maven project or null if it can't be built.
     */
    private IMavenProject getMavenProject()
    {
        try
        {
            // resolveTransitively = true to cache the project before the classpath update
            return MavenManager.getMavenProjectManager().getMavenProject( getProject(), true );
        }
        catch ( CoreException e )
        {
            /* project doesn't build */
            MavenCoreActivator.getDefault().getMavenExceptionHandler().handle( getProject(), e );
            return null;
        }
    }

    /**
     * Handles updates to the generated resources and test resources when needed.
     * 
     * @param status the status of the in-progress build.
     * @throws CoreException if there is a problem updating the resources.
     */
    private void handleAllResources( BuildStatus status )
        throws CoreException
    {
        List<Resource> resources = status.mavenProject.getResources();
        List<Resource> testResources = status.mavenProject.getTestResources();
        doHandleResources( status, resources, RESOURCES_GOAL );
        doHandleResources( status, testResources, TEST_RESOURCES_GOAL );
    }

    /**
     * Utility method for factoring checking and refreshing of resources and testResources.
     * 
     * @param mavenProject the project being modified.
     * @param delta the changes being processed.
     * @param resources the list of resources declared in the pom. Can be either resources or testResources.
     * @throws CoreException if there is a problem updating the resources.
     */
    private void doHandleResources( BuildStatus status, List<Resource> resources, final String goal )
        throws CoreException
    {
        Map<Resource, IPath> resourcePathMap = getPathForResources( status.mavenProject, resources );

        for ( Map.Entry<Resource, IPath> e : resourcePathMap.entrySet() )
        {
            final Resource mavenResource = e.getKey();

            IResourceDelta deltaMember = status.delta.findMember( e.getValue() );
            if ( deltaMember != null )
            {
                DeltaVisitor deltaVisitor = new DeltaVisitor( goal, status.mavenProject, mavenResource );
                deltaMember.accept( deltaVisitor );
                if ( deltaVisitor.isMatched() )
                {
                    onResourcesChange( status.mavenProject, goal, status.monitor );
                }
            }
        }
    }

    /**
     * @param mavenProject
     * @param resourceList
     * @return
     */
    private Map<Resource, IPath> getPathForResources( IMavenProject mavenProject, List<Resource> resourceList )
    {
        Map<Resource, IPath> result = new HashMap<Resource, IPath>( resourceList.size() );
        IProject project = mavenProject.getProject();
        IWorkspaceRoot workspaceRoot = project.getWorkspace().getRoot();
        for ( Resource r : resourceList )
        {
            String dirName = r.getDirectory();
            File directory = new File( dirName );
            if ( directory.isAbsolute() )
            {
                // Make relative to the project.
                IContainer[] containers = workspaceRoot.findContainersForLocationURI( directory.toURI() );
                for ( IContainer c : containers )
                {
                    if ( !project.equals( c.getProject() ) )
                    {
                        // Mapped to a resource in a different project
                        continue;
                    }
                    result.put( r, c.getProjectRelativePath() );
                }
            }
            else
            {
                // amuino: I haven't seen a case for this, but maven code seems to imply it is possible.
                result.put( r, new Path( dirName ) );
            }
        }
        return result;
    }

    /**
     * Updates resources as required by the current build status.
     * 
     * @param status the current build status
     * @throws CoreException if there is a problem running maven to update the resources.
     */
    private void onResourcesChange( BuildStatus status )
        throws CoreException
    {
        onResourcesChange( status, true );
    }

    /**
     * Updates resources as required by the current build status.
     * 
     * @param status the current build status
     * @param force set to <code>true</code> to process any resource not yet refreshed. Use <code>false</code> to
     *            only update resources which are filtered.
     * @throws CoreException if there is a problem running maven to update the resources.
     */
    private void onResourcesChange( BuildStatus status, boolean force )
        throws CoreException
    {
        if ( !status.resourcesRefreshed && ( force || isAnyResourceFiltered( status.mavenProject.getResources() ) ) )
        {
            onResourcesChange( status.mavenProject, RESOURCES_GOAL, status.monitor );
            status.resourcesRefreshed = true;
        }
        if ( !status.testResourcesRefreshed
            && ( force || isAnyResourceFiltered( status.mavenProject.getTestResources() ) ) )
        {
            onResourcesChange( status.mavenProject, TEST_RESOURCES_GOAL, status.monitor );
            status.resourcesRefreshed = true;
        }
    }

    /**
     * Runs the given refresh resources goal on a maven project.
     * 
     * @param mavenProject the maven project where the goal is to be executed.
     * @param goal the goal to execute.
     * @param monitor the progress monitor used for progress reporting and job cancellation.
     * @throws CoreException if there is a problem executing the goal.
     * @see #RESOURCES_GOAL
     * @see #TEST_RESOURCES_GOAL
     */
    private void onResourcesChange( IMavenProject mavenProject, String phase, IProgressMonitor monitor )
        throws CoreException
    {
        MavenJdtCoreActivator.trace( TraceOption.MAVEN_INCREMENTAL_BUILDER, "Processing resources on ", getProject(),
                                     " : ", phase );
        
        MavenExecutionParameter params = MavenExecutionParameter.newDefaultMavenExecutionParameter();
        params.setRecursive( false );
        if ( RESOURCES_GOAL.equals( phase ) )
        {
            params.setFilteredGoals( MavenPropertyManager.getInstance().getResourceExcludedGoals( mavenProject.getProject() ) );
        }
        else if ( TEST_RESOURCES_GOAL.equals( phase ) )
        {
            params.setFilteredGoals( MavenPropertyManager.getInstance().getTestResourceExcludedGoals( mavenProject.getProject() ) );
        }
        MavenManager.getMaven().executeGoal( mavenProject, phase, params, monitor );

    }

    private void onPomChange( BuildStatus status )
    {
        IProject project = getProject();
        MavenManager.getMavenProjectManager().setMavenProjectModified( project );
        final IFile pom = project.getFile( IMavenProject.POM_FILENAME );

        try
        {
            new IWorkspaceRunnable()
            {
                public void run( IProgressMonitor monitor )
                    throws CoreException
                {
                    pom.deleteMarkers( MavenJdtCoreActivator.MARKER_ID, false, IResource.DEPTH_ZERO );
                }
            }.run( status.monitor );
        }
        catch ( CoreException ce )
        {
            MavenJdtCoreActivator.getLogger().log( ce );
        }
        UpdateClasspathJob.scheduleNewUpdateClasspathJob( project, false );
    }
}
