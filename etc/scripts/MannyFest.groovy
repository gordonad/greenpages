def dir = new File('.')
dir.traverse(nameFilter:~/.*\.jar/)  {
    if (it.isFile()) {
        println "\n\n$it"
        new java.util.jar.JarFile(it).manifest.mainAttributes.entrySet().each {
            print "checking '${it.key}' ..."
            if (it.key in ["Bundle-SymbolicName", "Bundle-Version"]) {
              println "${it.key}: ${it.value}"
            }
        }
    }
}

