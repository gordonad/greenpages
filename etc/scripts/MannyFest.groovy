import java.util.jar.JarFile

//import org.slf4j.Logger
//import org.slf4j.LoggerFactory

@Grab(group = 'org.eclipse.virgo.util', module = 'org.eclipse.virgo.util.parser.manifest', version = '3.5.0.M04')
@Grab(group = 'org.slf4j', module = 'slf4j-api', version = '1.6.6')

// TODO - Build package map <jar, map<header, vals>>
// TODO - Build export package List
// TODO - Build import package List

// TODO - Is there an OSGi class that parses bundle manifests?


def dir = new File('../..')
def headers = ['Bundle-ClassPath', 'Bundle-SymbolicName', 'Bundle-Version', 'DynamicImport-Package', 'Export-Package', 'Import-Package', 'Fragment-Host', 'Require-Bundle', 'Require-Capability']
// def fullList = ['Bundle-ActivationPolicy','Bundle-Activator','Bundle-Category','Bundle-ClassPath','Bundle-ContactAddress','Bundle-Copyright','Bundle-Description','Bundle-DocURL','Bundle-Icon','Bundle-License','Bundle-Localization','Bundle-ManifestVersion','Bundle-Name','Bundle-NativeCode','Bundle-SymbolicName','Bundle-UpdateLocation','Bundle-Vendor','Bundle-Version','DynamicImport-Package','Export-Package','Import-Package','Fragment-Host','Provided-Capability','Require-Bundle','Require-Capability']

println "Manny is scanning for jar files..."
dir.traverse(nameFilter: ~/.*\.jar/) {
    if (it.isFile()) {
        def String jarFileName = it
        jarFileName = jarFileName.substring(jarFileName.lastIndexOf('/') + 1)
        println "***** $jarFileName *****"
//        if (JarFile(it).manifest) {

            new JarFile(it).manifest.mainAttributes.entrySet().each {
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
//        } else {
//            logger.error "NOT OSGi Compatible!"
//        }
    }
}
