//BundleDeps.groovy

import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.jar.JarFile;
import java.util.*;
import java.util.zip.ZipEntry;


class Pair {
  String name;
  String version;
  String fileName;

  Pair(String name, String version) {
    this.name = name;
    this.version = version;
  }

  Pair(String name, String version, String fileName) {
    this.name = name;
    this.version = version;
    this.fileName = fileName;
  }

  String toString() {
    return "Pair {" + name + ", " + version + ", " + fileName + "}";
  }
}

public class BundleDeps {

  private Manifest getManifest(String jarFileName) throws IOException {
    JarFile jarFile = new JarFile(jarFileName);

    Manifest manifest = getManifest(jarFile);

    jarFile.close();

    return manifest;
  }

  private Manifest getManifest(JarFile jarFile) throws IOException {
    Manifest manifest = null;

    ZipEntry zipEntry = jarFile.getEntry("META-INF/MANIFEST.MF");

    if (zipEntry == null) {
      zipEntry = jarFile.getEntry("meta-inf/manifest.mf");
    }

    if (zipEntry != null) {
      final InputStream is = jarFile.getInputStream(zipEntry);

      manifest = new Manifest(is);

      is.close();
    }

    return manifest;
  }

  private List retrieveExports(File dir) {
    List list = new ArrayList();

    File[] files = dir.listFiles();

    for(int i=0; i < files.length; i++) {
      if(files[i].getName().endsWith(".jar")) {
        Manifest manifest = getManifest(files[i].getPath());

        if(manifest != null) {
          Attributes mainAttributes = manifest.getMainAttributes();

          String exportPackage = mainAttributes.getValue("Export-Package");
          String bundleVersion = mainAttributes.getValue("Bundle-Version");

          if(exportPackage != null) {
            StringTokenizer st = new StringTokenizer(exportPackage, ",");

            while(st.hasMoreTokens()) {
              String token = st.nextToken();

              String name;

              int index = token.indexOf(";");

              if(index != -1) {
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

  private List retrieveImports(File file) {
    List list = new ArrayList();

    Manifest manifest = getManifest(file.getPath());

    if(manifest != null) {
      Attributes mainAttributes = manifest.getMainAttributes();

      String importPackage = mainAttributes.getValue("Import-Package");

      if(importPackage != null) {

        StringTokenizer st = new StringTokenizer(importPackage, ";\",=", true);

        while(st.hasMoreTokens()) {
          String name = st.nextToken();

          st.nextToken(); // ;

          String token = st.nextToken();

          String version = "";

          if(!token.equals(",")) {
            if(token.startsWith("version")) {
              st.nextToken(); // =
              st.nextToken(); // "

              token = st.nextToken();

              while(!token.equals("\"")) {
                version += token;

                token = st.nextToken();
              }

              if(st.hasMoreTokens()) {
                st.nextToken(); // ,
              }
            }

            list.add(new Pair(name, version));
          }
        }
      }
    }

    return list;
  }

  private Pair findExport(String name, List exportedPackages) {
    Pair pair = null;

    for(int i=0; i < exportedPackages.size() && pair == null; i++) {
      Pair exportedPackage = (Pair)exportedPackages.get(i);

      if(exportedPackage.name.equals(name)) {
        pair = exportedPackage;
      }
    }

    return pair;
  }

  public void analyze(String bundleJarName) {
    System.out.println("Input bundle jar:" + bundleJarName)

    File pluginJars = new File(new File(bundleJarName).getParent());

    List exportedPackages = retrieveExports(pluginJars);

    List importedPackages = retrieveImports(new File(bundleJarName));

    List usedJars = new ArrayList();

    System.out.println("Discovered dependencies:")

    def builder = new groovy.xml.MarkupBuilder()

    builder.dependencies() {
      for(int i=0; i < importedPackages.size(); i++) {
        Pair importedPackage = (Pair)importedPackages.get(i);

        Pair exportPair = findExport(importedPackage.name, exportedPackages);

        def fileName = exportPair.fileName.replace('\\', '/')

        if(!usedJars.contains(fileName)) {
          usedJars.add(fileName);

          def index1 = fileName.indexOf("_")
          def index2 = fileName.lastIndexOf(".jar")

          def groupAndName = fileName.substring(fileName.lastIndexOf('/')+1, index1);
          def name = groupAndName.substring(groupAndName.lastIndexOf(".")+1)

          def version1 = fileName.substring(index1+1, index2);

          dependency() {
            groupId(groupAndName)
            artifactId(name)
            version(version1.substring(0, version1.lastIndexOf(".")))
            scope("system")
            systemPath(exportPair.fileName.replace('\\', '/'))
          }
        }
      }
    }
  }

}

//String eclipseHome = "c:/Work/eclipse";

//String bundleJarName = eclipseHome + "/plugins/" + "org.eclipse.equinox.http.jetty_1.0.1.R33x_v20070816.jar";

BundleDeps analyzer = new BundleDeps();

analyzer.analyze(bundleJarName);
