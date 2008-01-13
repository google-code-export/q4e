/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom400;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extensions Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom400.ExtensionsType#getExtension <em>Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom400.mavenPackage#getExtensionsType()
 * @model extendedMetaData="name='extensions_._type' kind='elementOnly'"
 * @generated
 */
public interface ExtensionsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Extension</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.maven.pom400.Extension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension</em>' containment reference list.
	 * @see org.apache.maven.pom400.mavenPackage#getExtensionsType_Extension()
	 * @model type="org.apache.maven.pom400.Extension" containment="true"
	 *        extendedMetaData="kind='element' name='extension' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getExtension();

} // ExtensionsType