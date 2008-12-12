/**
 * Copyright (c) 2007-2008 DevZuz, Inc. (AKA Simula Labs, Inc.) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *
 * $Id$
 */
package org.devzuz.q.maven.pom.impl;

import org.devzuz.q.maven.pom.Organization;
import org.devzuz.q.maven.pom.PomPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Organization</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.devzuz.q.maven.pom.impl.OrganizationImpl#getName <em>Name</em>}</li>
 * <li>{@link org.devzuz.q.maven.pom.impl.OrganizationImpl#getUrl <em>Url</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OrganizationImpl
    extends EObjectImpl
    implements Organization
{
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getUrl() <em>Url</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getUrl()
     * @generated
     * @ordered
     */
    protected static final String URL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getUrl()
     * @generated
     * @ordered
     */
    protected String url = URL_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected OrganizationImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return PomPackage.Literals.ORGANIZATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName()
    {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName( String newName )
    {
        String oldName = name;
        name = newName;
        if ( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET, PomPackage.ORGANIZATION__NAME, oldName, name ) );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setUrl( String newUrl )
    {
        String oldUrl = url;
        url = newUrl;
        if ( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET, PomPackage.ORGANIZATION__URL, oldUrl, url ) );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType )
    {
        switch ( featureID )
        {
            case PomPackage.ORGANIZATION__NAME:
                return getName();
            case PomPackage.ORGANIZATION__URL:
                return getUrl();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet( int featureID, Object newValue )
    {
        switch ( featureID )
        {
            case PomPackage.ORGANIZATION__NAME:
                setName( (String) newValue );
                return;
            case PomPackage.ORGANIZATION__URL:
                setUrl( (String) newValue );
                return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset( int featureID )
    {
        switch ( featureID )
        {
            case PomPackage.ORGANIZATION__NAME:
                setName( NAME_EDEFAULT );
                return;
            case PomPackage.ORGANIZATION__URL:
                setUrl( URL_EDEFAULT );
                return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet( int featureID )
    {
        switch ( featureID )
        {
            case PomPackage.ORGANIZATION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals( name );
            case PomPackage.ORGANIZATION__URL:
                return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals( url );
        }
        return super.eIsSet( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString()
    {
        if ( eIsProxy() )
            return super.toString();

        StringBuffer result = new StringBuffer( super.toString() );
        result.append( " (name: " );
        result.append( name );
        result.append( ", url: " );
        result.append( url );
        result.append( ')' );
        return result.toString();
    }

} // OrganizationImpl
