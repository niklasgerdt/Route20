##..Java 8.. JAVA 7 FOR NOW, BECAUSE OF SONARQUBE NOT WORKING WITH J8

This project uses Java 8. Java 8 can be downloaded from here: https://jdk8.java.net/download.html. Eclipse with Java 8 support can be downloaded from here: http://downloads.efxclipse.org/eclipse-java8/. To create eclipse settings with maven use the following command: mvn eclipse:clean eclipse:eclipse. Make sure you have Java 8 installed and on the path. Run source ./setup_java8.sh or . ./setup_java8.sh (alter paths if necessary). Configure file setup_java8.sh for your evironment.

Possible issues:
- eclipse is not using java 8 as jdk
- eclipse is using embedded maven
- using maven inside eclipse messes eclipse project, so favor command line

Possiblesolutions
- disable maven nature from the eclipse project
- run mvn eclipse:eclipse from the command line when eclipse is running

Distributed system uses Java 7 for now.

##Building
Each project uses Maven and are builded separately. Distributed system uses embedded Jetty for quick testing. To use Jetty, run mvn jetty:run. Continuous integration is done with Jenkins. Url to Jenkins builds: . 

##Version control
We use git: https://github.com/niklasgerdt/Route20.git

##Quality control
SonarQube analysis tool is added to every Jenkins build. Url to quality reports: .
