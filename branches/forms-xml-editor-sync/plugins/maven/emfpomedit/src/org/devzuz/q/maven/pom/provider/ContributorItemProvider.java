/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.devzuz.q.maven.pom.provider;


import java.util.Collection;
import java.util.List;

import org.devzuz.q.maven.pom.Contributor;
import org.devzuz.q.maven.pom.PomFactory;
import org.devzuz.q.maven.pom.PomPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.devzuz.q.maven.pom.Contributor} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContributorItemProvider
    extends ItemProviderAdapter
    implements	
        IEditingDomainItemProvider,	
        IStructuredItemContentProvider,	
        ITreeItemContentProvider,	
        IItemLabelProvider,	
        IItemPropertySource		
{
    /**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ContributorItemProvider(AdapterFactory adapterFactory)
    {
		super(adapterFactory);
	}

    /**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
    {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addEmailPropertyDescriptor(object);
			addUrlPropertyDescriptor(object);
			addOrganizationPropertyDescriptor(object);
			addOrganizationUrlPropertyDescriptor(object);
			addTimezonePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

    /**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addNamePropertyDescriptor(Object object)
    {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Contributor_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Contributor_name_feature", "_UI_Contributor_type"),
				 PomPackage.Literals.CONTRIBUTOR__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Email feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addEmailPropertyDescriptor(Object object)
    {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Contributor_email_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Contributor_email_feature", "_UI_Contributor_type"),
				 PomPackage.Literals.CONTRIBUTOR__EMAIL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Url feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addUrlPropertyDescriptor(Object object)
    {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Contributor_url_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Contributor_url_feature", "_UI_Contributor_type"),
				 PomPackage.Literals.CONTRIBUTOR__URL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Organization feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addOrganizationPropertyDescriptor(Object object)
    {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Contributor_organization_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Contributor_organization_feature", "_UI_Contributor_type"),
				 PomPackage.Literals.CONTRIBUTOR__ORGANIZATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Organization Url feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addOrganizationUrlPropertyDescriptor(Object object)
    {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Contributor_organizationUrl_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Contributor_organizationUrl_feature", "_UI_Contributor_type"),
				 PomPackage.Literals.CONTRIBUTOR__ORGANIZATION_URL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Timezone feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addTimezonePropertyDescriptor(Object object)
    {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Contributor_timezone_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Contributor_timezone_feature", "_UI_Contributor_type"),
				 PomPackage.Literals.CONTRIBUTOR__TIMEZONE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
    {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(PomPackage.Literals.CONTRIBUTOR__ROLES);
			childrenFeatures.add(PomPackage.Literals.CONTRIBUTOR__PROPERTIES);
		}
		return childrenFeatures;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child)
    {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

    /**
	 * This returns Contributor.gif.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object getImage(Object object)
    {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Contributor"));
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getText(Object object)
    {
		String label = ((Contributor)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Contributor_type") :
			getString("_UI_Contributor_type") + " " + label;
	}

    /**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void notifyChanged(Notification notification)
    {
		updateChildren(notification);

		switch (notification.getFeatureID(Contributor.class)) {
			case PomPackage.CONTRIBUTOR__NAME:
			case PomPackage.CONTRIBUTOR__EMAIL:
			case PomPackage.CONTRIBUTOR__URL:
			case PomPackage.CONTRIBUTOR__ORGANIZATION:
			case PomPackage.CONTRIBUTOR__ORGANIZATION_URL:
			case PomPackage.CONTRIBUTOR__TIMEZONE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case PomPackage.CONTRIBUTOR__ROLES:
			case PomPackage.CONTRIBUTOR__PROPERTIES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

    /**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
    {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(PomPackage.Literals.CONTRIBUTOR__ROLES,
				 PomFactory.eINSTANCE.createRolesType()));

		newChildDescriptors.add
			(createChildParameter
				(PomPackage.Literals.CONTRIBUTOR__PROPERTIES,
				 PomFactory.eINSTANCE.createProperties()));
	}

    /**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public ResourceLocator getResourceLocator()
    {
		return PomEditPlugin.INSTANCE;
	}

}