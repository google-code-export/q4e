/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.devzuz.q.maven.pom;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contributors Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.devzuz.q.maven.pom.ContributorsType#getContributor <em>Contributor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.devzuz.q.maven.pom.PomPackage#getContributorsType()
 * @model extendedMetaData="name='contributors_._type' kind='elementOnly'"
 * @generated
 */
public interface ContributorsType extends EObject
{
    /**
     * Returns the value of the '<em><b>Contributor</b></em>' containment reference list.
     * The list contents are of type {@link org.devzuz.q.maven.pom.Contributor}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contributor</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Contributor</em>' containment reference list.
     * @see org.devzuz.q.maven.pom.PomPackage#getContributorsType_Contributor()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='contributor' namespace='##targetNamespace'"
     * @generated
     */
    EList<Contributor> getContributor();

} // ContributorsType