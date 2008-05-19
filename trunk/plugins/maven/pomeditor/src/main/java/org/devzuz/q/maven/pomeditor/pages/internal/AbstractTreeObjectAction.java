package org.devzuz.q.maven.pomeditor.pages.internal;

import java.util.ArrayList;
import java.util.List;

import org.devzuz.q.maven.pomeditor.components.ITreeObjectAction;

public abstract class AbstractTreeObjectAction
    implements ITreeObjectAction
{
    private String name;
    private List<ITreeObjectActionListener> listeners;
    
    public AbstractTreeObjectAction( String name )
    {
        this.name = name;
        listeners = new ArrayList<ITreeObjectActionListener>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName( String name )
    {
        this.name = name;
    }
    
    public void addTreeObjectActionListener( ITreeObjectActionListener listener )
    {
        listeners.add( listener );
    }
    
    public void removeTreeObjectActionListener( ITreeObjectActionListener listener )
    {
        listeners.remove( listener );
    }
    
    public void doAction( Object obj )
    {
        for( ITreeObjectActionListener listener : listeners )
        {
            listener.afterAction();
        }
    }
}