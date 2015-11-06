# Gradle
Gradle provides:

* A very flexible general purpose build tool like Ant.
* Switchable, build-by-convention frameworks a la Maven. But we never lock you in!
* Very powerful support for multi-project builds.
* Very powerful dependency management (based on Apache Ivy).
* Full support for your existing Maven or Ivy repository infrastructure.
* Support for transitive dependency management without the need for remote repositories or pom.xml and ivy.xml files.
* Ant tasks and builds as first class citizens.
* Groovy build scripts.
* A rich domain model for describing your build. 

This demo provides a gradle multi-project build.  A multi-project build in gradle consists of one root project, and one or more subprojects that may also have subprojects. 

## Gradle Project Structure
There are a number of Gradle files with the build:

* [build.gradle](../code/build.gradle) - parent build file, contains common definitions for subprojects.
* [environment.gradle](../code/environment.gradle) - contains a mechanism for defining the concept of a profile using gradle properties.  This is leveraged to supply application.properties for mariadb and a logback configuration that writes a file to docker log volume.
* [gradle.properties](../code/gradle.properties) - common properties for configuring gradle.
* [settings.gradle](../code/settings.gradle) - defines subprojects that should be included by the parent in a build.
* bootstrap 
    * [build.gradle](../code/bootstrap/build.gradle) - bootstrap module specific configuration.
* model
    * [build.gradle](../code/model/build.gradle) - model module specific configuration.  Includes a dependency on [lombok](http://jnb.ociweb.com/jnb/jnbJan2010.html) for reducing boilerplate code for the entity beans.
* backend
    * [build.gradle](../code/backend/build.gradle) - backend module specific configuration.  This module leverages model and bootstrap to build a configuration that starts and runs a jvm.  This is the core module for the demo.
    
    
## backend's build.gradle
This is the primary build file in the demo project.  It provides support for the following:

* Defining the main class to be invoked (from the bootstrap project)
* Setting any service specific jvm args
* Defining a gradle task to unzip the build artifact into a volume mount point 'volume-backend' (auto-generated at build time) in the [docker](../docker/) directory.  