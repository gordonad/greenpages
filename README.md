Eclipse Virgo GreenPages Demo
=============================
### [This Repository](https://github.com/gordonad/greenpages "Greenpages")

### This project is a Spring Maven project demonstrating OSGi application configuration for deployment on Eclipse Virgo.

### [Eclipse Virgo](http://www.eclipse.org/virgo/ "Virgo")
### [Eclipse Virgo](http://www.eclipse.org/virgo/samples/ "Samples")


Changes
-------
- greenpages-parent-solution pom.xml
  - Spring version 3.0.7 and surefire plugin to use corresponding spring instrument version
  - Dependencies versions specified in dependencyManagement section


TODO
----
- Update to Spring 3.1.1
- Update slf4j to 1.6.4
- Update logback to 1.0.0
- Provide DB connection for MySQL/MariaDB
- JPA 2.0.0
- EclipseLink 2.0.0(?)


Project Layout
--------------


Virgo Version Configuration
---------------------
- Virgo Dependencies
  - Created Spring 3.0.7 libd file
  - Added instrument jar to libd file (not sure if necessary)
  - Replaced Spring 3.0.5 jars with 3.0.7 in $VWS_HOME/repository/ext

- Virgo Files
  - $VWS_HOME/configuration/org.eclipse.virgo.kernel.userregion.properties



Application Version Configuration
---------------------------------
- When changing dependencies, ONLY change the template.mf file, Maven Bundlor plugin will automatically generate the correct MANIFEST.MF file
- Updating Application version should be done in all pom.xml and template.mf files



H2 Database
------------
- Database created in $USER_HOME/greenpages-db/greenpages
- Driver JAR - $USER_HOME/.m2/repository/com/h2database/com.springsource.org.h2/1.0.71/com.springsource.org.h2-1.0.71.jar
- Driver URL -
- Port 9092
- Userid: greenpages
- Password: pass
-



Versions
--------
Spring: 3.0.7
Hibernate: 3.6.9
Logback 0.9.28
SLF4j 1.6.1
JUnit: 4.10.0
JPA: 1.0.0


Installation
------------

### From solution:

Execute:

`mvn clean package`

Copy the solution/target/greenpages*.par to $VWS_HOME/pickup directory



Contact Me
----------
http://technophile.gordondickens.com
@gdickens
gordon@gordondickens.com

