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
// TODO - Allow single file scan
// TODO - Add File write option for report

def File dir = new File('../..')
def isDirectory = true
def headers = ['Bundle-ClassPath', 'Bundle-SymbolicName', 'Bundle-Version', 'DynamicImport-Package', 'Export-Package', 'Import-Package', 'Fragment-Host', 'Require-Bundle', 'Require-Capability']
// def fullList = ['Bundle-ActivationPolicy','Bundle-Activator','Bundle-Category','Bundle-ClassPath','Bundle-ContactAddress','Bundle-Copyright','Bundle-Description','Bundle-DocURL','Bundle-Icon','Bundle-License','Bundle-Localization','Bundle-ManifestVersion','Bundle-Name','Bundle-NativeCode','Bundle-SymbolicName','Bundle-UpdateLocation','Bundle-Vendor','Bundle-Version','DynamicImport-Package','Export-Package','Import-Package','Fragment-Host','Provided-Capability','Require-Bundle','Require-Capability']

if (args?.length == 1) {
    File checkArg = new File(args[0])
    if (checkArg && checkArg.isDirectory()) {
        isDirectory = true
        dir = checkArg
    } else {
        isDirectory = false
        println "Argument MUST be a directory, '${args[0]}' is not a valid directory."
        return
    }
}

if (isDirectory) {
    def jarCount = 0
    println "Manny is scanning for jar files in $dir ..."
    dir.traverse(nameFilter: ~/.*\.jar/) { daFile ->
        if (daFile.isFile()) {
            jarCount++
            processFile daFile, headers
        }
    }

    println "Checked Directory: $dir - found $jarCount jars"
}

private void processFile(File jarFileName, List headers) {
    String filePath = jarFileName.getCanonicalPath()
    println "***** PATH = '$filePath' *****"
    filePath = filePath.substring(filePath.lastIndexOf('/') + 1)
    println "***** $filePath *****"
    JarFile jarFile = new JarFile(jarFileName)
    if (jarFile.manifest?.mainAttributes?.entrySet()) {
        Manifest manifest = jarFile.manifest
        manifest.mainAttributes.entrySet().each { it ->
            def String myKey = it.key
            def String myVal = it.value
            if (myKey in headers) {
                if (myKey == 'Import-Package') {
                    println "$myKey: " + parseVersion(myVal)
                } else {
                    //TODO - don't replace commas in version ranges
                    myVal = myVal.replaceAll ",", "\n\t"
                    println "$myKey: $myVal"
                }
            }
        }
        println "\n"
    } else {
        println "********** NO Manifest - NOT OSGi Compatible! **********\n"
    }
}

