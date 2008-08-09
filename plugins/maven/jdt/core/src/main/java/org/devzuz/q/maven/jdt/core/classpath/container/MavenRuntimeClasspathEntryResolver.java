package org.devzuz.q.maven.jdt.core.classpath.container;import java.util.ArrayList;import java.util.List;import org.eclipse.core.resources.IProject;import org.eclipse.core.resources.ResourcesPlugin;import org.eclipse.core.runtime.CoreException;import org.eclipse.core.runtime.IPath;import org.eclipse.debug.core.ILaunchConfiguration;import org.eclipse.jdt.core.IClasspathContainer;import org.eclipse.jdt.core.IClasspathEntry;import org.eclipse.jdt.core.IJavaProject;import org.eclipse.jdt.core.JavaCore;import org.eclipse.jdt.internal.core.JavaProject;import org.eclipse.jdt.internal.launching.DefaultEntryResolver;import org.eclipse.jdt.internal.launching.RuntimeClasspathEntry;import org.eclipse.jdt.launching.IRuntimeClasspathEntry;import org.eclipse.jdt.launching.IRuntimeClasspathEntryResolver;import org.eclipse.jdt.launching.IVMInstall;import org.eclipse.jdt.launching.JavaRuntime;/** * Resolved for the Maven classpath container. *  * This container knows how to <em>look inside</em> the Maven Classpath Container and delegates handling of each * classpath entry to JDT. *  * @author amuino */public class MavenRuntimeClasspathEntryResolver implements IRuntimeClasspathEntryResolver{    public IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry( IRuntimeClasspathEntry entry, IJavaProject project )        throws CoreException    {        return resolveRuntimeClasspathEntry( entry );    }    public IVMInstall resolveVMInstall( IClasspathEntry entry ) throws CoreException    {        return null;    }    public IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry( IRuntimeClasspathEntry entry,                                                                  ILaunchConfiguration configuration )    throws CoreException    {        return resolveRuntimeClasspathEntry( entry );    }    protected IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry( IRuntimeClasspathEntry entry)        throws CoreException    {        IJavaProject project = entry.getJavaProject();        IPath path = entry.getPath();        IClasspathContainer container = JavaCore.getClasspathContainer( path, project );        IClasspathEntry[] entries = container.getClasspathEntries();        List<IRuntimeClasspathEntry> resolved = new ArrayList<IRuntimeClasspathEntry>();        for ( int i = 0; i < entries.length; i++ )        {            IClasspathEntry currentEntry = entries[i];            if ( currentEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY ) {                IRuntimeClasspathEntry runtimeEntry = JavaRuntime.newArchiveRuntimeClasspathEntry( currentEntry.getPath() );                resolved.add(runtimeEntry);            } else if ( currentEntry.getEntryKind() == IClasspathEntry.CPE_PROJECT )             {                IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject(currentEntry.getPath().toString());                IJavaProject jp = JavaCore.create( p );                IRuntimeClasspathEntry runtimeEntry = JavaRuntime.newProjectRuntimeClasspathEntry( jp );                resolved.add( runtimeEntry );            }            else            {                throw new IllegalArgumentException( "Classpath entries can only be of types LIBRARY or PROJECT" );            }        }        return resolved.toArray( new IRuntimeClasspathEntry[resolved.size()] );    }}