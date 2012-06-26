import java.util.jar.Attributes
import java.util.jar.JarFile
import java.util.jar.Manifest

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

if (args?.length == 1) {
    dir = args[0]
}

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

List retrieveExports(File dir) {
    List list = new ArrayList();

    File[] files = dir.listFiles();

    for (int i = 0; i < files.length; i++) {
        if (files[i].getName().endsWith(".jar")) {
            Manifest manifest = getManifest(files[i].getPath());

            if (manifest != null) {
                Attributes mainAttributes = manifest.getMainAttributes();

                String exportPackage = mainAttributes.getValue("Export-Package");
                String bundleVersion = mainAttributes.getValue("Bundle-Version");

                if (exportPackage != null) {
                    StringTokenizer st = new StringTokenizer(exportPackage, ",");

                    while (st.hasMoreTokens()) {
                        String token = st.nextToken();

                        String name;

                        int index = token.indexOf(";");

                        if (index != -1) {
                            name = token.substring(0, index);
                        }
                        else {
                            name = token;
                        }

                        list.add(new Pair(name, bundleVersion, files[i].getPath()));
                    }
                }
            }
        }
    }

    return list;
}

class Pair {
    String name
    String version
    String fileName

    Pair(String name, String version) {
        this.name = name
        this.version = version
    }

    Pair(String name, String version, String fileName) {
        this.name = name
        this.version = version
        this.fileName = fileName
    }

    String toString() {
        return "Pair {" + name + ", " + version + ", " + fileName + "}"
    }
}
