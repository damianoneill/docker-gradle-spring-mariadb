# Spring
The application leverages a number of Spring projects to provide infrastructure code.  Specifically it leverages:

* Spring Boot
    * Actuator - Spring Boot includes a number of additional features to help you monitor and manage your application when it’s pushed to production. 
    * Remote Shell - Spring Boot supports an integrated Java shell called ‘CRaSH’. You can use CRaSH to ssh into your running application.
    * Security - Extends the actuator functionality by exposing endpoints that contain sensitive information.
* Spring Data
    * JPA - aims to significantly improve the implementation of data access layers by reducing the effort to the amount that's actually needed.
    * Rest - provides a solid foundation on which to expose CRUD operations to your JPA Repository-managed entities using plain HTTP REST semantics.

## Spring Boot
Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that "just run". They take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need very little Spring configuration.


## Spring Data
Spring Data's mission is to provide a familiar and consistent, Spring-based programming model for data access while still retaining the special traits of the underlying data store. 

## Spring Subtleties 
* Auto-wiring - Ensure that the modules all exist in a package below com.example so that the beans can be auto-wired across different jars.
* Docker Linking - The application.properties for the dev environment has the spring.datasource.url defined, this points to jdbc:mariadb://db/sample, the db part is the name 
of the alias defined in the docker compose file [docker-compose.yml](../docker-compose.yml) for the mariadb container that is linked to the backend container.
* In the bootstrap module, there is a [config/application.properties](../code/bootstrap/src/main/resources/config/application.properties). By placing properties in the config directory they dont clash with the props defined in backend on 
the root classpath.  This allows reuse of properties across any module that links the bootstrap module.  It includes for e.g. values for actuator security enabling.

