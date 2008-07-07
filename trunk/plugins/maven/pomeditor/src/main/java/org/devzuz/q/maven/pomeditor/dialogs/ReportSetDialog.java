package org.devzuz.q.maven.pomeditor.dialogs;

import java.util.List;

import org.apache.maven.model.ReportSet;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.devzuz.q.maven.pomeditor.Messages;
import org.devzuz.q.maven.pomeditor.PomEditorActivator;
import org.devzuz.q.maven.ui.dialogs.AbstractResizableDialog;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;

public class ReportSetDialog
    extends AbstractResizableDialog
{
    public static ReportSetDialog newReportSetDialog()
    {
        return new ReportSetDialog( PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() );
    }

    private Button addReportSetButton;
    
    private Button editReportSetButton;
    
    private Button removeReportSetButton;

    private Button reportSetConfigurationButton;

    private Table reportSetTable;

    private List<ReportSet> reportSetList;

    public int selectedIndex;

    public ReportSet selectedReportSet;
    
    public boolean isPageModified;
    
    public ReportSetDialog( Shell parentShell )
    {
        super( parentShell );
    }

    @Override
    protected Preferences getDialogPreferences()
    {
        return PomEditorActivator.getDefault().getPluginPreferences();
    }

    @Override
    protected Control internalCreateDialogArea( Composite container )
    {
        container.setLayout( new GridLayout( 2, false ) );
        
        reportSetTable = new Table( container, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE );
        reportSetTable.setLayoutData( new GridData( GridData.FILL, GridData.FILL, true, true ) );
        reportSetTable.setLinesVisible( true );
        reportSetTable.setHeaderVisible( true );
        
        ReportSetTableListener tableListener = new ReportSetTableListener();
        reportSetTable.addSelectionListener( tableListener );
        
        TableColumn idColumn = new TableColumn( reportSetTable, SWT.BEGINNING, 0 );
        idColumn.setWidth( 50 );
        idColumn.setText( Messages.MavenPomEditor_MavenPomEditor_Identity );
        
        TableColumn inheritedColumn = new TableColumn( reportSetTable, SWT.BEGINNING, 1 );
        inheritedColumn.setWidth( 60 );
        inheritedColumn.setText( Messages.MavenPomEditor_MavenPomEditor_Inherited );
        
        TableColumn reportsColumn = new TableColumn( reportSetTable, SWT.BEGINNING, 2 );
        reportsColumn.setWidth( 200 );
        reportsColumn.setText( Messages.MavenPomEditor_MavenPomEditor_Report );
        
        Composite reportSetButtonContainer = new Composite( container, SWT.NULL );
        reportSetButtonContainer.setLayoutData( new GridData( GridData.CENTER, GridData.BEGINNING, false, true ) );
        RowLayout layout = new RowLayout( SWT.VERTICAL );
        layout.fill = true;
        reportSetButtonContainer.setLayout( layout );
        
        addReportSetButton = new Button( reportSetButtonContainer, SWT.PUSH | SWT.CENTER );
        addReportSetButton.setText( Messages.MavenPomEditor_MavenPomEditor_AddButton );
        AddReportSetButtonListener addButtonListener = new AddReportSetButtonListener();
        addReportSetButton.addSelectionListener( addButtonListener );
        addReportSetButton.setEnabled( true );
        
        editReportSetButton = new Button( reportSetButtonContainer, SWT.PUSH | SWT.CENTER );
        editReportSetButton.setText( Messages.MavenPomEditor_MavenPomEditor_EditButton );
        EditReportSetButtonListener editButtonListener = new EditReportSetButtonListener();
        editReportSetButton.addSelectionListener( editButtonListener );
        editReportSetButton.setEnabled( false );

        removeReportSetButton = new Button( reportSetButtonContainer, SWT.PUSH | SWT.CENTER );
        removeReportSetButton.setText( Messages.MavenPomEditor_MavenPomEditor_RemoveButton );
        RemoveReportSetButtonListener removeButtonListener = new RemoveReportSetButtonListener();
        removeReportSetButton.addSelectionListener( removeButtonListener );
        removeReportSetButton.setEnabled( false );
        
        reportSetConfigurationButton = new Button( reportSetButtonContainer, SWT.PUSH | SWT.CENTER );
        reportSetConfigurationButton.setText( Messages.MavenPomEditor_MavenPomEditor_Configuration );
        ReportSetConfigurationButtonListener configListener = new ReportSetConfigurationButtonListener();
        reportSetConfigurationButton.addSelectionListener( configListener );
        reportSetConfigurationButton.setEnabled( false );
        
        populateReportSetTable();
        
        return container;
    }
    
    @SuppressWarnings("unchecked")
    private void populateReportSetTable()
    {
        reportSetTable.removeAll();
        
        if ( reportSetList != null )
        {
            for ( ReportSet reportSet : reportSetList )
            {
                TableItem item = new TableItem( reportSetTable, SWT.BEGINNING );
                
                String reportsString = convertReportListToString( reportSet.getReports() );
                
                item.setText( new String[] { reportSet.getId(), reportSet.getInherited(),
                    reportsString } );
            }
        }        
    }
    
    public int opentWithReportSetList( List<ReportSet> reportSetList )
    {        
        this.reportSetList = reportSetList;
        
        return open();
    }
    
    private String convertReportListToString( List<String> reportsList )
    {   
        String reportString = "";
        int length = 0;
        
        for ( String report : reportsList )
        {
            reportString = reportString + report;
            length++;
            if ( length < reportsList.size() )
            {
                reportString = reportString + ", ";
            }
        }
        
        return reportString;
    }
    
    private boolean reportSetAlreadyExist( String id )
    {
        for ( ReportSet reportSet : reportSetList )
        {
            if ( reportSet.getId().equals( id ) )
            {
                return true;
            }
        }
        return false;
    }
    
    private class ReportSetTableListener extends SelectionAdapter
    {
        public void defaultWidgetSelected ( SelectionEvent e )
        {
            widgetSelected( e );
        }
        
        public void widgetSelected( SelectionEvent e )
        {
            TableItem[] item = reportSetTable.getSelection();
            
            if ( ( item != null ) && ( item.length > 0 ) )
            {
                editReportSetButton.setEnabled( true );
                removeReportSetButton.setEnabled( true );
                reportSetConfigurationButton.setEnabled( true );
                
                if ( reportSetTable.getSelectionIndex() >= 0 )
                {
                    selectedIndex = reportSetTable.getSelectionIndex();
                    selectedReportSet = reportSetList.get( selectedIndex );
                }                
            }
         }
    }
    
    private class AddReportSetButtonListener extends SelectionAdapter
    {
        public void defaultWidgetSelected ( SelectionEvent e )
        {
            widgetSelected( e );
        }
        
        public void widgetSelected( SelectionEvent e )
        {
            AddEditReportSetDialog addDialog = AddEditReportSetDialog.newAddEditReportSetDialog();
            
            if ( addDialog.open() == Window.OK )
            {
                ReportSet reportSet = new ReportSet();
                
                reportSet.setId( addDialog.getId() );
                
                if ( addDialog.isInherited() == true )
                {
                    reportSet.setInherited( "true" );
                }
                else
                {
                    reportSet.setInherited( "false" );
                }
                
                if ( ( addDialog.getReports() != null ) &&
                     ( addDialog.getReports() != "" ) )
                {
                    String reports = addDialog.getReports();
                    
                    String[] reportsList = reports.split( "," );
                    
                    for ( String str: reportsList )
                    {
                        reportSet.addReport( str.trim() );
                    }
                }
                else
                {
                    reportSet.setReports( null );
                }
                
                reportSetList.add( reportSet );
                
                setPageModified( true );
                
                populateReportSetTable();
                
            }
        }
    }
    
    private class EditReportSetButtonListener extends SelectionAdapter
    {
        public void defaultWidgetSelected ( SelectionEvent e )
        {
            widgetSelected( e );
        }
        
        public void widgetSelected( SelectionEvent e )
        {
            AddEditReportSetDialog editDialog = AddEditReportSetDialog.newAddEditReportSetDialog();
            
            if ( editDialog.openWithReportSet( selectedReportSet ) == Window.OK )
            {
                ReportSet newReportSet = new ReportSet();
                
                newReportSet.setId( editDialog.getId() );
                if ( editDialog.isInherited() == true )
                {
                    newReportSet.setInherited( "true" );
                }
                else
                {
                    newReportSet.setInherited( "false" );
                }
                
                if ( ( editDialog.getReports() != null ) &&
                     ( editDialog.getReports() != "" ) )
                {
                    String reports = editDialog.getReports();
                    
                    String[] reportsList = reports.split( "," );
                    
                    for ( String str: reportsList )
                    {
                        newReportSet.addReport( str.trim() );
                    }
                }
                else
                {
                    newReportSet.setReports( null );
                }
                
                if ( reportSetAlreadyExist( newReportSet.getId() ) )
                {
                    if ( selectedReportSet.getId().equalsIgnoreCase( newReportSet.getId() ) )
                    {
                        reportSetList.remove( selectedReportSet );
                        
                        reportSetList.add( newReportSet );
                        
                        setPageModified( true );
                        
                        populateReportSetTable();
                    }
                    else
                    {
                        MessageBox mesgBox = new MessageBox( getShell(), SWT.ICON_ERROR | SWT.OK  );
                        mesgBox.setMessage( "Report Set already exists." );
                        mesgBox.setText( "Saving Report Set Error" );
                        mesgBox.open( );
                    }
                }
                else
                {
                    reportSetList.remove( selectedReportSet );
                    
                    reportSetList.add( newReportSet );
                    
                    setPageModified( true );
                    
                    populateReportSetTable();
                }
            }
        }
    }
    
    private class RemoveReportSetButtonListener extends SelectionAdapter
    {
        public void defaultWidgetSelected ( SelectionEvent e )
        {
            widgetSelected( e );
        }
        
        public void widgetSelected( SelectionEvent e )
        {
            for ( int index = 0; index < reportSetList.size(); index++ )
            {
                if ( index == reportSetTable.getSelectionIndex() )
                {
                    ReportSet reportSet = (ReportSet) reportSetList.get( index );
                    reportSetList.remove( reportSet );
                }
            }
            
            setPageModified( true );
            
            populateReportSetTable();
        }
    }
    
    private class ReportSetConfigurationButtonListener extends SelectionAdapter
    {
        public void defaultWidgetSelected ( SelectionEvent e )
        {
            widgetSelected( e );
        }
        
        public void widgetSelected( SelectionEvent e )
        {
            ConfigurationDialog configDialog = ConfigurationDialog.newConfigurationDialog();
            
            Xpp3Dom dom = ( Xpp3Dom )selectedReportSet.getConfiguration();
            
            if ( configDialog.openWithConfiguration( dom ) == Window.OK )
            {
                System.out.println("moogle testing #4 kupo");
            }
        }
    }

    public boolean isPageModified()
    {
        return isPageModified;
    }

    public void setPageModified( boolean isPageModified )
    {
        this.isPageModified = isPageModified;
    }

}