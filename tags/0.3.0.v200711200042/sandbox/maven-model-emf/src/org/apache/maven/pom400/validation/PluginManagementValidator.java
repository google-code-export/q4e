/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom400.validation;

import org.apache.maven.pom400.PluginsType3;

/**
 * A sample validator interface for {@link org.apache.maven.pom400.PluginManagement}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface PluginManagementValidator {
	boolean validate();

	boolean validatePlugins(PluginsType3 value);
}