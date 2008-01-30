package org.devzuz.q.maven.embedder;

import java.util.Properties;

import org.codehaus.plexus.logging.Logger;

public class MavenExecutionParameter
{
    private Properties executionProperties;
    
    private boolean    useReactor = false;
    
    private boolean    recursive = false;
    
    private boolean    showErrors = false;
    
    private boolean    offline = false;
    
    private boolean    updateSnapshots = false;
    
    private boolean    noSnapshotUpdates = false;
    
    private int        loggingLevel = Logger.LEVEL_INFO;
    
    public static MavenExecutionParameter newDefaultMavenExecutionParameter()
    {
        return new MavenExecutionParameter(); 
    }
    
    public static MavenExecutionParameter newDefaultMavenExecutionParameter( Properties properties )
    {
        return new MavenExecutionParameter( properties ); 
    }
    
    private MavenExecutionParameter()
    {
        executionProperties = null;
    }
    
    private MavenExecutionParameter( Properties properties )
    {
        setExecutionProperties( properties );
    }
    
    public Properties getExecutionProperties()
    {
        return executionProperties;
    }
    public void setExecutionProperties( Properties executionProperties )
    {
        this.executionProperties = executionProperties;
    }
    public boolean isUseReactor()
    {
        return useReactor;
    }
    public void setUseReactor( boolean useReactor )
    {
        this.useReactor = useReactor;
    }
    public boolean isRecursive()
    {
        return recursive;
    }
    public void setRecursive( boolean recursive )
    {
        this.recursive = recursive;
    }
    public boolean isShowErrors()
    {
        return showErrors;
    }
    public void setShowErrors( boolean showErrors )
    {
        this.showErrors = showErrors;
    }
    public boolean isOffline()
    {
        return offline;
    }
    public void setOffline( boolean offline )
    {
        this.offline = offline;
    }
    public boolean isUpdateSnapshots()
    {
        return updateSnapshots;
    }
    public void setUpdateSnapshots( boolean updateSnapshots )
    {
        this.updateSnapshots = updateSnapshots;
    }
    public boolean isNoSnapshotUpdates()
    {
        return noSnapshotUpdates;
    }
    public void setNoSnapshotUpdates( boolean noSnapshotUpdates )
    {
        this.noSnapshotUpdates = noSnapshotUpdates;
    }

    public int getLoggingLevel()
    {
        return loggingLevel;
    }

    public void setLoggingLevel( int loggingLevel )
    {
        this.loggingLevel = loggingLevel;
    }
    
    
    
}