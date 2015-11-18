# Docker Gradle Spring Mariadb demo

This project demonstrates docker as a deployment environment for a multi-process java db project.

![Alt text](./docs/demo.png?raw=true "Demo Architecture")

It uses a variety of gradle plugins for e.g.

* Fingbugs - The FindBugs plugin performs quality checks on your project's Java source files using FindBugs and generates reports from these checks.
* Checkstyle - The Checkstyle plugin performs quality checks on your project's Java source files using Checkstyle and generates reports from these checks.
* Spring-boot - The Spring Boot Gradle Plugin provides Spring Boot support in Gradle, allowing you to package executable jar or war archives, run Spring Boot applications and omit version information from your build.gradle file for “blessed” dependencies.


## Installation

The assumption is that the developer is using a OSX development environment.

The following prerequisites are required:

- Homebrew
- Java 8
- Gradle
- Virtualbox
- Docker Toolbox
- Docker Kitematic - a GUI for interfacing with running docker instances

### Homebrew and dependencies install

Install homebrew 

    $ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)" 

Install Cask

    $ brew install caskroom/cask/brew-cask 
     
Install Java

    $ brew update && brew cask install java 
     
Install Gradle

    $ brew install gradle 
     
Install Virtualbox 

    $ brew cask install virtualbox 

Install Docker Toolbox 

    $ brew install Caskroom/cask/dockertoolbox 
    
Install Kitematic

    $ brew install Caskroom/cask/kitematic 
    

### Docker Setup
Instructions for setting up a virtualbox hosting a docker os can be found here [docker.md](./docs/docker.md).
    
## Usage

### Build the code
Gradle is used to build the code.  The gradle build process can be supplied with a property that influences the artifacts that get built
and what build phases get executed.

    $ cd code
    
To build a configuration that executes the tests and runs up an instance using a in memory database on the localhost without docker, the 'test' profile can be selected.
    
    $ gradle -Penv=test clean build bootRun
    
To build a configuration for deploying on Docker, the 'dev' profile can be selected.
    
    $ gradle -Penv=dev clean build

### Spin up the docker images
Once a build with the 'dev' environment has been built, Docker compose can be used to spin up the various docker containers.

    $ docker-compose -f ../docker-compose.yml up
    
### Test the deployment
Once the containers are running, you can call the HAL Resource directly 

    $ curl http://admin:admin@<DOCKER_HOST>:8080/customers

The ip address for the virtualbox container (DOCKER_HOST) can be found in your environment after invoking the following command.

    $ eval "$(docker-machine env default)"
    $ env | grep DOCKER_HOST
    
Accessing the integrated Java Shell [crash](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-remote-shell.html), can be done by sshing to the remote shell port exposed through the vm

    $ ssh -p 2000 admin@<DOCKER_HOST>


## Extras
A Docker Compose configuration is included for spinning up a sonarqube environment, this can be found here [sonar-docker-compose.yml](./codequality/sonar-docker-compose.yml)

This persists to a docker linked mariadb instance.  Note the /var/lib/mysql directory is not a VOLUME in the docker config as their are challenges in permissions on OSX.  Therefore if you rm the container you loose the previously capture sonar data.

The main gradle file includes a sonar runner definition and configuration to use the docker environment.  This can be invoked in the code directory as follows:

    $ gradle sonarRunner

## Technology Uses
This section provides an overview on each of the main technologies used: 

* [Docker](./docs/docker.md) - document describing howto setup Docker for this project.
* [Gradle](./docs/gradle.md) - document describing how gradle has been configured for this project.
* [Intellij](./docs/intellij.md) - document describing howto generate intellij compatible configuration.
* [Spring](./docs/spring.md) - document describing the spring components used.

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## License

Copyright [2015] [Damian ONeill]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
