package com.chariotsolutions.greenpages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: gordondickens
 * Date: 4/26/12
 * Time: 11:33 AM
 */
public class BaseTestConfig {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTestConfig.class);
    //todo - externalize settings, at a minimum get the user.home
    protected static final String REPOSITORY_ROOT = "/Users/gordondickens/.m2/repository";
    protected static final String DEFAULT_MODULE_VERSION = "2.4.3.RELEASE";
    protected static final String DEFAULT_MODULE_GROUP = "org.eclipse.virgo";
    protected static final String EQUINOX_VERSION = "3.6.2";

//    public Option[] getConfig(String moduleGroup, String moduleArtifact, String moduleVersion) {
//logger.debug("******* CURRENT EQUINOX VERSION '{}' ********", equinox().getVersion());
//        return options(
//                mavenBundle(moduleGroup, moduleArtifact, DEFAULT_MODULE_VERSION),
//                bundle(getFileLocationFromRepository(moduleGroup, moduleArtifact, moduleVersion)),
//                junitBundles(),
//                equinox().version(EQUINOX_VERSION)
//        );
//    }
//
//    public Option[] getConfig(String moduleArtifact, String moduleVersion) {
//        return getConfig(DEFAULT_MODULE_GROUP, moduleArtifact, moduleVersion);
//    }
//
//    public Option[] getConfig(String moduleArtifact) {
//        return getConfig(DEFAULT_MODULE_GROUP, moduleArtifact, DEFAULT_MODULE_VERSION);
//    }
//
//    /**
//     * Calculate the path to the jar for testing.
//     * Any parameter that is null throw an Assertion Error
//     *
//     * @param moduleGroup    - Maven Group
//     * @param moduleArtifact - Maven Artifact (required)
//     * @param moduleVersion  - Maven Version
//     * @return path to the jar
//     */
//    private String getFileLocationFromRepository(String moduleGroup, String moduleArtifact, String moduleVersion) {
//        assertNotNull("Module Group is REQUIRED", moduleGroup);
//        assertNotNull("Module Artifact is REQUIRED", moduleArtifact);
//        assertNotNull("Module Version is REQUIRED", moduleVersion);
//
//        moduleGroup = StringUtils.trim(moduleGroup);
//        moduleArtifact = StringUtils.trim(moduleArtifact);
//        moduleVersion = StringUtils.trim(moduleVersion);
//
//        String packagePath = StringUtils.replaceChars(moduleGroup, '.', '/');
//        String results = REPOSITORY_ROOT + "/" + packagePath + "/" + moduleArtifact +
//                "/" + moduleVersion + "/" + moduleArtifact + "-" + moduleVersion + ".jar";
//
//        logger.debug("Jar Path: {}", results);
//        return results;
//
//    }
//
//
//    /**
//     * Test can be enabled to test the repository file path builder
//     */
//    @Ignore
//    @Test
//    public void testGetFileLocationFromRepository() {
//        String jarPath = "/Users/gordondickens/.m2/repository/org/eclipse/virgo/greenpages.app-solution/2.4.3.RELEASE/greenpages.app-solution-2.4.3.RELEASE.jar";
//        String group = DEFAULT_MODULE_GROUP;
//        String artifact = "greenpages.app-solution";
//        String version = DEFAULT_MODULE_VERSION;
//
//        String generatedLocation = getFileLocationFromRepository(group, artifact, version);
//        assertEquals(jarPath, generatedLocation);
//
//        group = "com.chariotsolutions.demo";
//        artifact = "greenpages-demo";
//        version = "1.2.3";
//
//        assertEquals("/Users/gordondickens/.m2/repository/com/chariotsolutions/demo/greenpages-demo/1.2.3/greenpages-demo-1.2.3.jar", getFileLocationFromRepository(group, artifact, version));
//
//
//        group = "  com.chariotsolutions.demo";
//        artifact = "greenpages-demo  ";
//        version = " 1.2.3 ";
//
//        assertEquals("/Users/gordondickens/.m2/repository/com/chariotsolutions/demo/greenpages-demo/1.2.3/greenpages-demo-1.2.3.jar", getFileLocationFromRepository(group, artifact, version));
//
//        group = "\tcom.chariotsolutions.demo";
//        artifact = "\tgreenpages-demo";
//        version = "\t1.2.3\t";
//
//        assertEquals("/Users/gordondickens/.m2/repository/com/chariotsolutions/demo/greenpages-demo/1.2.3/greenpages-demo-1.2.3.jar", getFileLocationFromRepository(group, artifact, version));
//
//
//        group = null;
//        artifact = "\tgreenpages-demo";
//        version = "\t1.2.3\t";
//
////        assertEquals("/Users/gordondickens/.m2/repository/com/chariotsolutions/demo/greenpages-demo/1.2.3/greenpages-demo-1.2.3.jar", getFileLocationFromRepository(group, artifact, version));
//
//    }
}
