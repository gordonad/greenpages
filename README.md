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
- Update slf4j to 1.6.4
- Update logback to 1.0.0
- Provide DB connection for MySQL/MariaDB
- Update to Spring 3.1.1


Project Layout
--------------


Version Configuration
---------------------
- Virgo Dependencies
  - Created Spring 3.0.7 libd file
  - Added instrument jar to libd file (not sure if necessary)
  - Replaced Spring 3.0.5 jars with 3.0.7 in <virgoTomcatHome>/repository/ext




H2 Database
------------


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

Copy the solution/target/greenpages*.par to <virgoTomcatDir>/pickup directory



Contact Me
----------
http://technophile.gordondickens.com
@gdickens
gordon@gordondickens.com

