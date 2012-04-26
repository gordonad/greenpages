package com.chariotsolutions.greenpages;

/**
 * User: gordondickens
 * Date: 4/26/12
 * Time: 11:22 AM
 */

import greenpages.Directory;
import greenpages.Listing;
import greenpages.internal.ImmutableListing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.*;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SampleTest extends BaseTestConfig {
    private static final Listing ROD_JOHNSON = new ImmutableListing(1, "Rod", "Johnson", "rod.johnson@springsource.com");


    @Inject
    private Directory directory;

    @Configuration
    public Option[] config() {

        return options(
                mavenBundle("org.eclipse.virgo", "greenpages.app-solution", DEFAULT_MODULE_VERSION),
                bundle(REPOSITORY_ROOT + "/org/eclipse/virgo/greenpages.app-solution/" + DEFAULT_MODULE_VERSION + "/greenpages.app-solution-" + DEFAULT_MODULE_VERSION + ".jar"),
                junitBundles(),
                equinox().version("3.6.2")
        );
    }

    @Test
    public void testFindListing() {
        assertNotNull(directory);
        assertEquals(ROD_JOHNSON.getLastName().equalsIgnoreCase("johnson"), directory.findListing(1));
    }
}