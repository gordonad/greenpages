import org.eclipse.virgo.util.osgi.manifest.internal.StandardBundleManifest
import org.eclipse.virgo.util.osgi.manifest.parse.ParserLogger
import org.eclipse.virgo.util.parser.manifest.ManifestContents
import org.eclipse.virgo.util.parser.manifest.RecoveringManifestParser

import java.util.jar.Attributes
import java.util.jar.JarFile
import java.util.jar.Manifest

//import org.slf4j.Logger
//import org.slf4j.LoggerFactory


@Grab(group = 'org.eclipse.virgo.util', module = 'org.eclipse.virgo.util.parser.manifest', version = '3.5.0.M04')
@Grab(group = 'org.eclipse.virgo.util', module = 'org.eclipse.virgo.util.osgi.manifest', version = '3.5.0.M04')
@Grab(group = 'org.osgi', module = 'org.osgi.core', version = '4.2.0')

//@Grab(group = 'org.slf4j', module = 'slf4j-api', version = '1.6.6')
//@Grab(group = 'ch.qos.logback', module = 'logback-classic', version = '1.0.6')

// TODO - Build package map <jar, map<header, vals>>
// TODO - Build export package List
// TODO - Build import package List

// TODO - Is there an OSGi class that parses bundle manifests?


def dir = new File('../..')
def headers = ['Bundle-SymbolicName', 'Bundle-Version']

//def headers = ['Bundle-ClassPath', 'Bundle-SymbolicName', 'Bundle-Version', 'DynamicImport-Package', 'Export-Package', 'Import-Package', 'Fragment-Host', 'Require-Bundle', 'Require-Capability']

// def fullList = ['Bundle-ActivationPolicy','Bundle-Activator','Bundle-Category','Bundle-ClassPath','Bundle-ContactAddress','Bundle-Copyright','Bundle-Description','Bundle-DocURL','Bundle-Icon','Bundle-License','Bundle-Localization','Bundle-ManifestVersion','Bundle-Name','Bundle-NativeCode','Bundle-SymbolicName','Bundle-UpdateLocation','Bundle-Vendor','Bundle-Version','DynamicImport-Package','Export-Package','Import-Package','Fragment-Host','Provided-Capability','Require-Bundle','Require-Capability']

println "Manny is scanning for jar files..."
dir.traverse(nameFilter: ~/.*\.jar/) {
    if (it.isFile()) {
        def String jarFileName = it
        jarFileName = jarFileName.substring(jarFileName.lastIndexOf('/') + 1)
        println "***** $jarFileName *****"
        JarFile jarFile = new JarFile(it)
        if (jarFile.manifest?.mainAttributes?.entrySet()) {
            Manifest manifest = jarFile.manifest
//            println "Your Manifest sir: ${manifest.dump()}"
            Attributes attributes = manifest.mainAttributes
            RecoveringManifestParser parser = new RecoveringManifestParser()
            ManifestContents manifestContents = parser.parse(manifest.dump())
            StandardBundleManifest standardBundleManifest = new StandardBundleManifest(new SimpleParserLogger(), manifestContents.getMainAttributes())
//            println "Virgo Manifest Parser ${manifestContents.toString()}"
            jarFile.manifest.mainAttributes.entrySet().each {
//            print "checking '${it.key}' ..."
                def String myKey = it.key
                def String myVal = it.value
                if (myKey in headers) {
                    //TODO - don't replace commas in version ranges
                    myVal = myVal.replaceAll ",", "\n\t"
                    println "$myKey: $myVal"
                }
            }
            println "\n"
        } else {
            println "********** NO Manifest - NOT OSGi Compatible! **********\n"
        }
    }
}


class SimpleParserLogger implements ParserLogger {

    /**
     * {@inheritDoc}
     */
    public String[] errorReports() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void outputErrorMsg(Exception re, String item) {
        println "Error: $item"
    }
}
