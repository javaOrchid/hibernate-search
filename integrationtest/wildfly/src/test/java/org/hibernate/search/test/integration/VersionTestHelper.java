/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.test.integration;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.spec.se.manifest.ManifestDescriptor;

/**
 * Helper class for integration testing of the JBoss Modules generated by the
 * hibernate-search-modules Maven module.
 *
 * The slot version is set as a property in the parent pom, and passed to the JVM
 * of Arquillian as a system property of the Maven maven-failsafe-plugin.
 *
 * @since 5.0
 */
public class VersionTestHelper {

	private static final String wildflySearchModuleDependency = "org.hibernate.search.orm:" + getVersionString() + " services";

	private VersionTestHelper() {
		//not meant to be created
	}

	/**
	 * @return the slot version of the Hibernate Search modules generated by this project
	 */
	private static String getVersionString() {
		return System.getProperty( "hibernate.search.module.slot.version" );
	}

	/**
	 * @return the StringAsset to be used in a Manifest descriptor to enable the Hibernate-Search-ORM module
	 */
	public static Asset moduleDependencyManifest() {
		String manifest = Descriptors.create( ManifestDescriptor.class )
				.attribute( "Dependencies", wildflySearchModuleDependency )
				.exportAsString();
		return new StringAsset( manifest );
	}

	/**
	 * Adds the needed Manifest to a deployment to enable the Hibernate-Search-ORM module
	 * @param archive
	 */
	public static void addDependencyToSearchModule(Archive<?> archive) {
		archive.add( VersionTestHelper.moduleDependencyManifest(), "META-INF/MANIFEST.MF" );
	}

	public static String getDependencyVersionLucene() {
		return System.getProperty( "dependency.version.Lucene" );
	}

	public static String getDependencyVersionHibernateCommonsAnnotations() {
		return System.getProperty( "dependency.version.HibernateCommonsAnnotations" );
	}
}