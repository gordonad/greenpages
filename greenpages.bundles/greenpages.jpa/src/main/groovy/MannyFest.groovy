println "Manny is scanning for jar files..."
def dir = new File('../../../..')

def myList = ['Bundle-SymbolicName', 'Bundle-Version']

dir.traverse(nameFilter: ~/.*\.jar/) {
    if (it.isFile()) {
        def String jarFileName = it
        jarFileName = jarFileName.substring(jarFileName.lastIndexOf('/') + 1)
        println "** $jarFileName **"
        new java.util.jar.JarFile(it).manifest.mainAttributes.entrySet().each {
//            print "checking '${it.key}' ..."
            def String myVal = it.key
            if (myVal in myList) {
                println "${myVal}: ${it.value}"
            }
        }
        println "\n"
    }
}

