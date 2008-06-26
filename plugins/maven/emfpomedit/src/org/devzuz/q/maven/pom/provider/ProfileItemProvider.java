/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.devzuz.q.maven.pom.provider;


import java.util.Collection;
import java.util.List;

import org.devzuz.q.maven.pom.PomFactory;
import org.devzuz.q.maven.pom.PomPackage;
import org.devzuz.q.maven.pom.Profile;

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
 * This is the item provider adapter for a {@link org.devzuz.q.maven.pom.Profile} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProfileItemProvider
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
    public ProfileItemProvider(AdapterFactory adapterFactory)
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
        if (itemPropertyDescriptors == null)
        {
            super.getPropertyDescriptors(object);

            addIdPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Id feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addIdPropertyDescriptor(Object object)
    {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Profile_id_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Profile_id_feature", "_UI_Profile_type"),
                 PomPackage.Literals.PROFILE__ID,
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
        if (childrenFeatures == null)
        {
            super.getChildrenFeatures(object);
            childrenFeatures.add(PomPackage.Literals.PROFILE__ACTIVATION);
            childrenFeatures.add(PomPackage.Literals.PROFILE__BUILD);
            childrenFeatures.add(PomPackage.Literals.PROFILE__MODULES);
            childrenFeatures.add(PomPackage.Literals.PROFILE__REPOSITORIES);
            childrenFeatures.add(PomPackage.Literals.PROFILE__PLUGIN_REPOSITORIES);
            childrenFeatures.add(PomPackage.Literals.PROFILE__DEPENDENCIES);
            childrenFeatures.add(PomPackage.Literals.PROFILE__REPORTS);
            childrenFeatures.add(PomPackage.Literals.PROFILE__REPORTING);
            childrenFeatures.add(PomPackage.Literals.PROFILE__DEPENDENCY_MANAGEMENT);
            childrenFeatures.add(PomPackage.Literals.PROFILE__DISTRIBUTION_MANAGEMENT);
            childrenFeatures.add(PomPackage.Literals.PROFILE__PROPERTIES);
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
     * This returns Profile.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object)
    {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Profile"));
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
        String label = ((Profile)object).getId();
        return label == null || label.length() == 0 ?
            getString("_UI_Profile_type") :
            getString("_UI_Profile_type") + " " + label;
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

        switch (notification.getFeatureID(Profile.class))
        {
            case PomPackage.PROFILE__ID:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case PomPackage.PROFILE__ACTIVATION:
            case PomPackage.PROFILE__BUILD:
            case PomPackage.PROFILE__MODULES:
            case PomPackage.PROFILE__REPOSITORIES:
            case PomPackage.PROFILE__PLUGIN_REPOSITORIES:
            case PomPackage.PROFILE__DEPENDENCIES:
            case PomPackage.PROFILE__REPORTS:
            case PomPackage.PROFILE__REPORTING:
            case PomPackage.PROFILE__DEPENDENCY_MANAGEMENT:
            case PomPackage.PROFILE__DISTRIBUTION_MANAGEMENT:
            case PomPackage.PROFILE__PROPERTIES:
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
                (PomPackage.Literals.PROFILE__ACTIVATION,
                 PomFactory.eINSTANCE.createActivation()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__BUILD,
                 PomFactory.eINSTANCE.createBuildBase()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__MODULES,
                 PomFactory.eINSTANCE.createModulesType1()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__REPOSITORIES,
                 PomFactory.eINSTANCE.createRepositoriesType1()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__PLUGIN_REPOSITORIES,
                 PomFactory.eINSTANCE.createPluginRepositoriesType1()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__DEPENDENCIES,
                 PomFactory.eINSTANCE.createDependenciesType2()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__REPORTS,
                 PomFactory.eINSTANCE.createReportsType2()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__REPORTING,
                 PomFactory.eINSTANCE.createReporting()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__DEPENDENCY_MANAGEMENT,
                 PomFactory.eINSTANCE.createDependencyManagement()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__DISTRIBUTION_MANAGEMENT,
                 PomFactory.eINSTANCE.createDistributionManagement()));

        newChildDescriptors.add
            (createChildParameter
                (PomPackage.Literals.PROFILE__PROPERTIES,
                 PomFactory.eINSTANCE.createPropertiesType1()));
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
