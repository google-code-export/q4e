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
 * A representation of the model object '<em><b>Distribution Management</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.0.0
 * 
 *         This elements describes all that pertains to distribution for a project.
 *         It is primarily used for deployment of artifacts and the site
 *         produced by the build.
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom400.DistributionManagement#getRepository <em>Repository</em>}</li>
 *   <li>{@link org.apache.maven.pom400.DistributionManagement#getSnapshotRepository <em>Snapshot Repository</em>}</li>
 *   <li>{@link org.apache.maven.pom400.DistributionManagement#getSite <em>Site</em>}</li>
 *   <li>{@link org.apache.maven.pom400.DistributionManagement#getDownloadUrl <em>Download Url</em>}</li>
 *   <li>{@link org.apache.maven.pom400.DistributionManagement#getRelocation <em>Relocation</em>}</li>
 *   <li>{@link org.apache.maven.pom400.DistributionManagement#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom400.mavenPackage#getDistributionManagement()
 * @model extendedMetaData="name='DistributionManagement' kind='elementOnly'"
 * @generated
 */
public interface DistributionManagement extends EObject {
	/**
	 * Returns the value of the '<em><b>Repository</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             Information needed to deploy the artifacts generated by the project to a remote repository.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Repository</em>' containment reference.
	 * @see #setRepository(DeploymentRepository)
	 * @see org.apache.maven.pom400.mavenPackage#getDistributionManagement_Repository()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='repository' namespace='##targetNamespace'"
	 * @generated
	 */
	DeploymentRepository getRepository();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.DistributionManagement#getRepository <em>Repository</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repository</em>' containment reference.
	 * @see #getRepository()
	 * @generated
	 */
	void setRepository(DeploymentRepository value);

	/**
	 * Returns the value of the '<em><b>Snapshot Repository</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             Where to deploy snapshots of artifacts to. If not given, it defaults to the &lt;code&gt;repository&lt;/code&gt; element.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Snapshot Repository</em>' containment reference.
	 * @see #setSnapshotRepository(DeploymentRepository)
	 * @see org.apache.maven.pom400.mavenPackage#getDistributionManagement_SnapshotRepository()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='snapshotRepository' namespace='##targetNamespace'"
	 * @generated
	 */
	DeploymentRepository getSnapshotRepository();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.DistributionManagement#getSnapshotRepository <em>Snapshot Repository</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snapshot Repository</em>' containment reference.
	 * @see #getSnapshotRepository()
	 * @generated
	 */
	void setSnapshotRepository(DeploymentRepository value);

	/**
	 * Returns the value of the '<em><b>Site</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             Information needed for deploying the web site of the project.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Site</em>' containment reference.
	 * @see #setSite(Site)
	 * @see org.apache.maven.pom400.mavenPackage#getDistributionManagement_Site()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='site' namespace='##targetNamespace'"
	 * @generated
	 */
	Site getSite();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.DistributionManagement#getSite <em>Site</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site</em>' containment reference.
	 * @see #getSite()
	 * @generated
	 */
	void setSite(Site value);

	/**
	 * Returns the value of the '<em><b>Download Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0+
	 * 
	 *             The URL of the project's download page. If not given users will be referred to the homepage given by
	 *             &lt;code&gt;url&lt;/code&gt;. This is given to assist in locating artifacts that are not in the repository due to
	 *             licensing restrictions.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Download Url</em>' attribute.
	 * @see #setDownloadUrl(String)
	 * @see org.apache.maven.pom400.mavenPackage#getDistributionManagement_DownloadUrl()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='downloadUrl' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDownloadUrl();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.DistributionManagement#getDownloadUrl <em>Download Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Download Url</em>' attribute.
	 * @see #getDownloadUrl()
	 * @generated
	 */
	void setDownloadUrl(String value);

	/**
	 * Returns the value of the '<em><b>Relocation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             Relocation information of the artifact if it has been moved to a new group ID and/or artifact ID.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Relocation</em>' containment reference.
	 * @see #setRelocation(Relocation)
	 * @see org.apache.maven.pom400.mavenPackage#getDistributionManagement_Relocation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='relocation' namespace='##targetNamespace'"
	 * @generated
	 */
	Relocation getRelocation();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.DistributionManagement#getRelocation <em>Relocation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relocation</em>' containment reference.
	 * @see #getRelocation()
	 * @generated
	 */
	void setRelocation(Relocation value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             Gives the status of this artifact in the remote repository. This must not be set in your local
	 *             project, as it is updated by tools placing it in the reposiory. Valid values are: &lt;code&gt;none&lt;/code&gt; (default),
	 *             &lt;code&gt;converted&lt;/code&gt; (repository manager converted this from an Maven 1 POM), &lt;code&gt;partner&lt;/code&gt;
	 *             (directly synced from a partner Maven 2 repository), &lt;code&gt;deployed&lt;/code&gt; (was deployed from a Maven 2
	 *             instance), &lt;code&gt;verified&lt;/code&gt; (has been hand verified as correct and final).
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(String)
	 * @see org.apache.maven.pom400.mavenPackage#getDistributionManagement_Status()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='status' namespace='##targetNamespace'"
	 * @generated
	 */
	String getStatus();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom400.DistributionManagement#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(String value);

} // DistributionManagement