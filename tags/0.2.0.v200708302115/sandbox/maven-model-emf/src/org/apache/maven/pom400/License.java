/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom400;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>License</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 3.0.0+
 * 
 *         Describes the licenses for this project.  This is used to generate
 *         the license page of the project's web site, as well as being taken into consideration in other reporting and
 *         validation. The licenses listed for the project are that of the project itself, and not of dependencies.
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom400.License#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.maven.pom400.License#getUrl <em>Url</em>}</li>
 *   <li>{@link org.apache.maven.pom400.License#getDistribution <em>Distribution</em>}</li>
 *   <li>{@link org.apache.maven.pom400.License#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom400.mavenPackage#getLicense()
 * @model extendedMetaData="name='License' kind='elementOnly'"
 * @generated
 */
public interface License extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 3.0.0+
	 * The full legal name of the license.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.apache.maven.pom400.mavenPackage#getLicense_Name()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.License#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 3.0.0+
	 * The official url for the license text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.apache.maven.pom400.mavenPackage#getLicense_Url()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='url' namespace='##targetNamespace'"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.License#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Distribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 3.0.0+
	 * 
	 *               The primary method by which this project may be distributed.
	 *             &lt;dl&gt;
	 *               &lt;dt&gt;repo&lt;/dt&gt;
	 *               &lt;dd&gt;may be downloaded from the Maven repository&lt;/dd&gt;
	 *               &lt;dt&gt;manual&lt;/dt&gt;
	 *               &lt;dd&gt;user must manually download and install the dependency.&lt;/dd&gt;
	 *             &lt;/dl&gt;
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Distribution</em>' attribute.
	 * @see #setDistribution(String)
	 * @see org.apache.maven.pom400.mavenPackage#getLicense_Distribution()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='distribution' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDistribution();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.License#getDistribution <em>Distribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distribution</em>' attribute.
	 * @see #getDistribution()
	 * @generated
	 */
	void setDistribution(String value);

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 3.0.0+
	 * 
	 *             Addendum information pertaining to this license.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Comments</em>' attribute.
	 * @see #setComments(String)
	 * @see org.apache.maven.pom400.mavenPackage#getLicense_Comments()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='comments' namespace='##targetNamespace'"
	 * @generated
	 */
	String getComments();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.License#getComments <em>Comments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comments</em>' attribute.
	 * @see #getComments()
	 * @generated
	 */
	void setComments(String value);

} // License