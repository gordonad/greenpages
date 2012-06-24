package com.gordondickens.greenpages;


import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.jar.JarFile

/**
 * User: gordon
 * Date: 6/24/12
 * Time: 11:10 AM
 */
public class MantifestInfoTests {
    private static final Logger logger = LoggerFactory.getLogger(ManifestInfoTests.class);

    @Test
    public void logManifestDetails() {
        //This is a Groovy test
        logger.debug "Manny is scanning for jar files..."
        def dir = new File('../../..')

        def headers = ['Bundle-ClassPath', 'Bundle-SymbolicName', 'Bundle-Version', 'DynamicImport-Package', 'Export-Package', 'Import-Package', 'Fragment-Host', 'Require-Bundle', 'Require-Capability']
        // def fullList = ['Bundle-ActivationPolicy','Bundle-Activator','Bundle-Category','Bundle-ClassPath','Bundle-ContactAddress','Bundle-Copyright','Bundle-Description','Bundle-DocURL','Bundle-Icon','Bundle-License','Bundle-Localization','Bundle-ManifestVersion','Bundle-Name','Bundle-NativeCode','Bundle-SymbolicName','Bundle-UpdateLocation','Bundle-Vendor','Bundle-Version','DynamicImport-Package','Export-Package','Import-Package','Fragment-Host','Provided-Capability','Require-Bundle','Require-Capability']

        dir.traverse(nameFilter: ~/.*\.jar/) {
            if (it.isFile()) {
                def String jarFileName = it
                jarFileName = jarFileName.substring(jarFileName.lastIndexOf('/') + 1)
                logger.debug "***** $jarFileName *****"
                if (JarFile(it).manifest) {
                    new JarFile(it).manifest.mainAttributes.entrySet().each {
                        logger.trace "checking '${it.key}' ..."
                        def String myKey = it.key
                        def String myVal = it.value
                        if (myKey in headers) {
                            //TODO - don't replace commas in version ranges
                            myVal = myVal.replaceAll (",", "\n\t")
                            logger.debug "$myKey: $myVal"
                        }
                    }
                    logger.debug "\n"
                } else {
                    logger.error "NOT OSGi Compatible!"
                }
            }
        }
    }
}
