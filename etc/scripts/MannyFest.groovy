import java.util.jar.JarFile
import org.eclipse.virgo.util.osgi.manifest.parse.ParserLogger
import org.eclipse.virgo.util.osgi.manifest.internal.*
import org.eclipse.virgo.util.osgi.manifest.*

import java.util.jar.Manifest

//import org.slf4j.Logger
//import org.slf4j.LoggerFactory

@Grab(group = 'org.eclipse.osgi', module = 'org.eclipse.osgi', version = '3.7.1.R37x_v20110808-1106')
@Grab(group = 'org.eclipse.virgo.util', module = 'org.eclipse.virgo.util.parser.manifest', version = '3.5.0.M04')
@Grab(group = 'org.eclipse.virgo.util', module = 'org.eclipse.virgo.util.osgi.manifest', version = '3.5.0.M04')
@Grab(group = 'ch.qos.logback', module = 'logback-classic', version = '1.0.6')
@Grab(group = 'org.slf4j', module = 'slf4j-api', version = '1.6.6')

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
	List <Map <String, Map<String, String>>> results = new ArrayList<Map <String, Map<String, String>>>()
    dir.traverse(nameFilter: ~/.*\.jar/) { daFile ->
        if (daFile.isFile()) {
            jarCount++
            Map <String, Map<String, String>> jarDetail = processFile daFile, headers
			if (jarDetail) results.add(jarDetail)
        }
    }

    println "Checked Directory: $dir - found $jarCount jars"
	
//	if (results) println results.toString()
}

private Map <String, Map<String, String>> processFile(File jarFileName, List headers) {
	Map <String, Map<String, String>> jarDetails = new HashMap<String, Map<String, String>>()
    String filePath = jarFileName.getCanonicalPath()
//    println "***** PATH = '$filePath' *****"
    filePath = filePath.substring(filePath.lastIndexOf('/') + 1)
    println "***** $filePath *****"
    JarFile jarFile = new JarFile(jarFileName)
    if (jarFile.manifest?.mainAttributes?.entrySet()) {
        Manifest manifest = jarFile.manifest
		Map <String, String> jarEntries = new HashMap<String,String>()
        manifest.mainAttributes.entrySet().each { attrib ->
            def String myKey = attrib.key
            def String myVal = attrib.value
//            if (myKey in headers) {
//                if (myKey == 'Import-Package') {
//                    println "$myKey: $myVal"
//                } else {
                    //myVal = myVal.replaceAll ",", "\n\t"
//                    println "$myKey: $myVal"
					jarEntries.put(myKey, myVal)
//                }
//            }
        }
		jarDetails.put(jarFileName, jarEntries)
		buildManifest(jarEntries)
		
//        println "\n"
    } else {
        println "********** NO Manifest - NOT OSGi Compatible! **********\n"
    }
	return jarDetails
}

private void buildManifest(Map<String,String> manifest) {
	StandardBundleManifest mani = new StandardBundleManifest(new PrintingParserLogger(), manifest)
	ImportPackage importPackage = mani.importPackage
	List<ImportedPackage> pkgList =  importPackage.importedPackages
	pkgList.each { pkg ->
		println "<-- IMPORTED PKG ${pkg.packageName} - V: ${pkg.version.toString()}"
	}
	ExportPackage exportPackage = mani.exportPackage
	List<ExportedPackage> epkgList =  exportPackage.exportedPackages
	epkgList.each { pkg ->
		println "--> EXPORTED PKG ${pkg.packageName} - V: ${pkg.version.toString()}"
	}
//	println "I got your Manifest... Right Here ${mani.toString()}"
}

class PrintingParserLogger implements ParserLogger {
    public String[] errorReports() {
        return null;
    }

    public void outputErrorMsg(Exception re, String item) {   
		println "Error: $item"     
    }
}


