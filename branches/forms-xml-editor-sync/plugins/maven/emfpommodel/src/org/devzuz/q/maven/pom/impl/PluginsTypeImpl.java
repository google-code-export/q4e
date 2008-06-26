/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.devzuz.q.maven.pom.impl;

import java.util.Collection;

import org.devzuz.q.maven.pom.Plugin;
import org.devzuz.q.maven.pom.PluginsType;
import org.devzuz.q.maven.pom.PomPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plugins Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.devzuz.q.maven.pom.impl.PluginsTypeImpl#getPlugin <em>Plugin</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PluginsTypeImpl extends EObjectImpl implements PluginsType
{
    /**
     * The cached value of the '{@link #getPlugin() <em>Plugin</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPlugin()
     * @generated
     * @ordered
     */
    protected EList<Plugin> plugin;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PluginsTypeImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return PomPackage.Literals.PLUGINS_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Plugin> getPlugin()
    {
        if (plugin == null)
        {
            plugin = new EObjectContainmentEList<Plugin>(Plugin.class, this, PomPackage.PLUGINS_TYPE__PLUGIN);
        }
        return plugin;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case PomPackage.PLUGINS_TYPE__PLUGIN:
                return ((InternalEList<?>)getPlugin()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case PomPackage.PLUGINS_TYPE__PLUGIN:
                return getPlugin();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case PomPackage.PLUGINS_TYPE__PLUGIN:
                getPlugin().clear();
                getPlugin().addAll((Collection<? extends Plugin>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case PomPackage.PLUGINS_TYPE__PLUGIN:
                getPlugin().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case PomPackage.PLUGINS_TYPE__PLUGIN:
                return plugin != null && !plugin.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //PluginsTypeImpl