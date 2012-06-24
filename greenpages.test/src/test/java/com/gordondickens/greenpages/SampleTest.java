package com.gordondickens.greenpages;

/**
 * User: gordondickens
 * Date: 4/26/12
 * Time: 11:22 AM
 */


//@RunWith(JUnit4TestRunner.class)
//@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SampleTest extends BaseTestConfig {
//    private static final Listing ROD_JOHNSON = new ImmutableListing(1, "Rod", "Johnson", "rod.johnson@springsource.com");
//    private final String ARTIFACT_NAME = "greenpages.app-solution";
//    public static final String DEFAULT_VIRGO_DIRECTORY = "/opt/virgo-tomcat-server-3.5.0.M04";
//
//
//    //NOTE:
//    // @Before, @After, @BeforeClass, @AfterClass are NOT Supported
//    // Only field injection is supported.
//    // Constructor, setter or parameter injection will NOT work.
//
//
//    @Inject
//    private BundleContext bc;
//
//
//
//    //TODO - Can this be injected?  Can we use SpringJUnit4ClassRunner also?
////    @Inject
////    private Directory directory;
//
//    @Configuration
//    public Option[] config() {
//        logger.debug("***** Begin Pax Exam Config *****");
//        logger.debug("**** Virgo Directory '{}'", DEFAULT_VIRGO_DIRECTORY);
//        logger.debug("******* CURRENT EQUINOX VERSION '{}' ********", equinox().getVersion());
//        logger.debug("**** Bundle Context '{}'", bc);
//
//
//
//        String virgoUser = DEFAULT_VIRGO_DIRECTORY + "/repository/usr";
//        String virgoExternal = DEFAULT_VIRGO_DIRECTORY + "/repository/ext";
//        String virgoPlugins = DEFAULT_VIRGO_DIRECTORY + "/plugins";
//        String virgoLib = DEFAULT_VIRGO_DIRECTORY + "/lib";
//
//        String f = "*-*.jar";
//        DirScannerProvisionOption unfiltered = scanDir(virgoLib);
//        Option virgo = unfiltered.filter(f);
//        unfiltered = scanDir(virgoPlugins);
//        Option virgoPl = unfiltered.filter(f);
//        unfiltered = scanDir(virgoExternal);
//        Option virgoExt = unfiltered.filter(f);
//        unfiltered = scanDir(virgoUser);
//        Option virgoUsr = unfiltered.filter(f);
//
//
//        Option[] myoptions = options(virgo, virgoPl, virgoExt, virgoUsr,
//                mavenBundle(DEFAULT_MODULE_GROUP, ARTIFACT_NAME, DEFAULT_MODULE_VERSION),
//                bundle(REPOSITORY_ROOT + "/org/eclipse/virgo/greenpages.app-solution/" +
//                        DEFAULT_MODULE_VERSION + "/" + ARTIFACT_NAME + "-" + DEFAULT_MODULE_VERSION + ".jar"),
//                junitBundles(),
//                frameworkProperty("ECLIPSE_EE_INSTALL_VERIFY").value("false"),
//                equinox().version(EQUINOX_VERSION)
//        );
//
//        logger.debug("***** Returning Pax Exam Config {} *****", myoptions);
//
//        return myoptions;
//    }
//
//    @Test
//    public void testFindListing() {
//        logger.debug("TEST STARTED STUB");
//        assertNotNull("Directory MUST Exist", directory);
//        logger.debug("Directory = '{}'", directory.toString());

//        assertEquals("Person MUST Exist", ROD_JOHNSONgetLastName().equalsIgnoreCase("johnson"), directory.findListing(1));
//    }
}