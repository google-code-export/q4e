package org.devzuz.q.maven.pomeditor.components;

import java.util.List;

import org.devzuz.q.maven.pomeditor.model.TreeRoot;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class PluginTreeComponent extends Composite 
{
	private TreeViewer treeViewer;
	
	private IObjectActionMap objectActionMap;
	
	public PluginTreeComponent( Composite parent, int style )
	{
		super( parent, style );

        setLayout( new FillLayout() );
        
        treeViewer = new TreeViewer( this, SWT.BORDER );
        treeViewer.addSelectionChangedListener( new TreeViewerSelectionListener() );
        treeViewer.expandAll();
	}

	public IContentProvider getContentProvider()
	{
		return treeViewer.getContentProvider();
	}
	
	public void setContentProvider( IContentProvider provider )
	{
		treeViewer.setContentProvider( provider );
	}
	
	public IBaseLabelProvider getLabelProvider()
	{
		return treeViewer.getLabelProvider();
	}
	
	public void setLabelProvider( IBaseLabelProvider provider )
	{
		treeViewer.setLabelProvider( provider );
	}
	
	public void setInput( Object input )
	{
		treeViewer.setInput( input );
	}

    public IObjectActionMap getObjectActionMap()
    {
        return objectActionMap;
    }

    public void setObjectActionMap( IObjectActionMap objectActionMap )
    {
        this.objectActionMap = objectActionMap;
    }
    
    public ISelection getSelection()
    {
        return treeViewer.getSelection();
    }
    
    public void setSelection( ISelection selection )
    {
        treeViewer.setSelection( selection );
    }
    
    public void refresh()
    {
        treeViewer.refresh();
    }
    
    public void expandAll()
    {
        treeViewer.expandAll();
    }
    
    public void addSelectionChangeListener ( ISelectionChangedListener listener )
    {
        treeViewer.addSelectionChangedListener( listener );
    }
    
    public void removeSelectionChangeListener ( ISelectionChangedListener listener )
    {
        treeViewer.removeSelectionChangedListener( listener );
    }
    
    private class TreeViewerSelectionListener implements ISelectionChangedListener
    {
        public void selectionChanged( SelectionChangedEvent event )
        {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            treeViewer.getControl().setMenu( getMenu( selection.getFirstElement() ) );
        }

        private Menu getMenu( final Object firstElement )
        {
            List< ITreeObjectAction > actions = objectActionMap.getObjectActions( firstElement );            
            if ( actions != null )
            {
                Menu rootMenu = new Menu( PluginTreeComponent.this.getParent() );
                for ( final ITreeObjectAction action : actions )
                {
                    MenuItem item = new MenuItem( rootMenu, SWT.PUSH );
                    item.setText( action.getName() );
                    item.addSelectionListener( new SelectionAdapter()
                    {
                        public void widgetSelected( SelectionEvent e )
                        {
                        	Object target = firstElement;
                        	if( target instanceof TreeRoot )
                        	{
                        		target = ( (TreeRoot) target ).createOrGetModelRoot();
                        	}
                            action.doAction( target );
                        }
                    } );
                }
                return rootMenu;
            }
            return null;
        }
    }
}
