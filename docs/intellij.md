# Intellij
This document gives a brief overview of using intellij with the project.

Ensure the Gradle plugin is enabled.

    File -> New -> Project from existing sources ...

And select the docker-gradle-spring-mariadb directory.

After this is imported, none of the modules in the code directory will be configured. So cd into the code directory and run

    ./gradlew idea
    
This will create the intellij module configurations for each of the modules.

Now you can right mouse click on the iml files in the [code](../code) directories and 'Import "XXX" Module' to the project.
