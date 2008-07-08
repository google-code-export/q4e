package org.devzuz.q.maven.pom.translators;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.devzuz.q.maven.pom.PomFactory;
import org.devzuz.q.maven.pom.PomPackage;
import org.devzuz.q.maven.pom.PropertyElement;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Listens for notifications from the dom model and the E model, and syncs the models.
 * 
 * @author Mike Poindexter
 */
public class SSESyncAdapter
    extends TranslatorAdapter
    implements Adapter, INodeAdapter
{
    private SSESyncResource resource;

    private EObject eobject;

    private Notifier target;

    private Map<EStructuralFeature, TranslatorAdapter> childAdapters =
        new LinkedHashMap<EStructuralFeature, TranslatorAdapter>();

    public SSESyncAdapter( SSESyncResource resource, EObject eobject, Element node )
    {
        super( resource );
        this.eobject = eobject;
        this.node = node;
        this.resource = resource;
    }

    public boolean isAdapterForType( Object type )
    {
        return SSESyncAdapter.class.equals( type );
    }

    public void notifyChanged( INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue,
                               Object newValue, int pos )
    {
        if ( resource.isProcessEvents() )
        {
            resource.setProcessEvents( false );
            try
            {
                if ( INodeNotifier.ADD == eventType && newValue instanceof Element )
                {
                    if ( notifier == node )
                    {
                        IDOMElement addedElement = (IDOMElement) newValue;
                        EStructuralFeature feature =
                            eobject.eClass().getEStructuralFeature( addedElement.getLocalName() );
                        if ( feature != null )
                        {
                            createOrGetChildAdapter( feature ).load();
                        }
                    }
                }
                else if ( INodeNotifier.REMOVE == eventType && oldValue instanceof Element )
                {
                    if ( notifier == node )
                    {
                        IDOMElement removedElement = (IDOMElement) oldValue;
                        EStructuralFeature feature =
                            eobject.eClass().getEStructuralFeature( removedElement.getLocalName() );
                        if ( feature != null )
                        {
                            eobject.eUnset( feature );
                            childAdapters.remove( feature );
                        }
                    }
                }
            }
            finally
            {
                resource.setProcessEvents( true );
            }
        }
    }

    public void notifyChanged( Notification notification )
    {
        if ( resource.isProcessEvents() )
        {
            resource.setProcessEvents( false );
            try
            {
                EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
                switch ( notification.getEventType() )
                {

                    case Notification.SET:
                        if ( "".equals( notification.getNewValue() ) )
                        {
                            removeDomChild( feature );
                        }
                        else
                        {
                            TranslatorAdapter setAdapter = createOrGetChildAdapter( feature );
                            setAdapter.update( notification.getOldValue() );
                        }

                        break;

                    case Notification.ADD:
                        ListAdapter addListAdapter = (ListAdapter) createOrGetChildAdapter( feature );
                        addListAdapter.add( notification.getNewValue(), notification.getPosition() );
                        break;

                    case Notification.ADD_MANY:
                        ListAdapter addManyListAdapter = (ListAdapter) createOrGetChildAdapter( feature );
                        Collection<?> addMany = (Collection<?>) notification.getNewValue();
                        int addPosition = notification.getPosition();
                        int addIdx = addPosition;
                        for ( Object object : addMany )
                        {
                            addManyListAdapter.add( object,
                                                    addPosition == Notification.NO_INDEX ? Notification.NO_INDEX
                                                                    : addIdx );
                            addIdx++;
                        }
                        break;

                    case Notification.REMOVE:
                        ListAdapter removeListAdapter = (ListAdapter) createOrGetChildAdapter( feature );
                        removeListAdapter.remove( notification.getOldValue(), notification.getPosition() );
                        break;

                    case Notification.UNSET:
                        removeDomChild( feature );
                        break;

                    case Notification.REMOVE_MANY:
                        ListAdapter removeManyListAdapter = (ListAdapter) createOrGetChildAdapter( feature );
                        Collection<?> removeMany = (Collection<?>) notification.getOldValue();
                        int removePosition = notification.getPosition();
                        int removeIdx = removePosition;
                        for ( Object object : removeMany )
                        {
                            removeManyListAdapter.remove(
                                                          object,
                                                          removePosition == Notification.NO_INDEX ? Notification.NO_INDEX
                                                                          : removeIdx );
                            removeIdx++;
                        }
                        break;

                }
            }
            finally
            {
                resource.setProcessEvents( true );
            }
        }

    }

    @Override
    public void update( Object oldValue )
    {
        save();
    }

    private TranslatorAdapter createOrGetChildAdapter( EStructuralFeature feature )
    {
        TranslatorAdapter ret = childAdapters.get( feature );
        if ( null == ret )
        {
            Element element = getFirstChildWithName( node, feature.getName() );
            boolean isNew = false;
            if ( element == null )
            {
                element = node.getOwnerDocument().createElement( feature.getName() );
                insertElement( element );
                isNew = true;
            }

            if ( feature.isMany() )
            {
                EClass elementType = null;
                if ( feature instanceof EReference )
                {
                    elementType = ( (EReference) feature ).getEReferenceType();
                }

                if ( elementType != null
                    && elementType.getClassifierID() == PomPackage.Literals.PROPERTY_ELEMENT.getClassifierID() )
                {
                    ret = new PropertiesAdapter( resource, element, (List<PropertyElement>) eobject.eGet( feature ) );
                }
                else
                {
                    ret = new ListAdapter( resource, element, (List<?>) eobject.eGet( feature ), elementType );
                }
            }
            else if ( feature instanceof EReference )
            {
                EReference ref = (EReference) feature;
                EObject eo = (EObject) eobject.eGet( feature );
                if ( null == eo )
                {
                    eo = PomFactory.eINSTANCE.create( ref.getEReferenceType() );
                    eobject.eSet( ref, eo );
                }
                ret = new SSESyncAdapter( this.resource, eo, element );
            }
            else
            {
                ret = new ValueUpdateAdapter( resource, element, eobject, feature );
            }

            if ( ret instanceof SSESyncAdapter )
            {
                ( (SSESyncAdapter) ret ).eobject.eAdapters().add( (SSESyncAdapter) ret );
            }
            if ( isNew )
            {
                formatNode( element );
            }
            ( (IDOMElement) element ).addAdapter( ret );
            childAdapters.put( feature, ret );
        }
        return ret;
    }

    /**
     * Removes the dom child matching the feature.
     * 
     * @param feature
     */
    private void removeDomChild( EStructuralFeature feature )
    {
        TranslatorAdapter ta = childAdapters.get( feature );
        if ( ta != null )
        {
            removeChildElement( ta.getNode() );
        }
    }

    /**
     * Inserts an element under the current node, attempting to preserve proper node ordering.
     * 
     * @param element
     * @param position
     */
    private void insertElement( Element element )
    {
        Node beforeNode = null;
        boolean searching = false;
        for ( EStructuralFeature feature : eobject.eClass().getEStructuralFeatures() )
        {
            if ( element.getLocalName().equals( feature.getName() ) )
            {
                searching = true;
            }
            if ( searching )
            {
                beforeNode = getFirstChildWithName( node, feature.getName() );
                if ( beforeNode != null )
                {
                    break;
                }
            }
        }

        if ( beforeNode == null )
        {
            beforeNode = node.getLastChild();
            while ( !( beforeNode == null || beforeNode.getPreviousSibling() instanceof Element || beforeNode.getPreviousSibling() == null ) )
            {
                beforeNode = beforeNode.getPreviousSibling();
            }
        }

        if ( beforeNode == null )
        {
            node.appendChild( element );
        }
        else
        {
            node.insertBefore( element, beforeNode );
        }
    }

    /**
     * Populates all child model objects from the child nodes in the dom tree.
     */
    public void load()
    {
        NodeList children = node.getChildNodes();
        int nChildren = children.getLength();
        for ( int i = 0; i < nChildren; i++ )
        {
            Node child = children.item( i );
            if ( child instanceof Element )
            {
                Element element = (Element) child;
                EStructuralFeature feature = eobject.eClass().getEStructuralFeature( element.getLocalName() );
                if ( feature != null )
                {
                    createOrGetChildAdapter( feature ).load();
                }
            }

        }
    }

    /**
     * Populates all child dom objects from the model children.
     */
    public void save()
    {
        for ( EStructuralFeature feature : eobject.eClass().getEStructuralFeatures() )
        {
            if ( eobject.eIsSet( feature ) )
            {
                Object o = eobject.eGet( feature );
                if ( !"".equals( o ) && o != null )
                {
                    createOrGetChildAdapter( feature ).save();
                }
            }
        }
    }

    public Notifier getTarget()
    {
        return target;
    }

    public void setTarget( Notifier target )
    {
        this.target = target;
    }

}