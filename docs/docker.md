# Docker
Docker containers wrap up a piece of software in a complete filesystem that contains everything it needs to run: code, runtime, system tools, system libraries – anything you can install on a server. This guarantees that it will always run the same, regardless of the environment it is running in. 

Containers running on a single machine all share the same operating system kernel so they start instantly and make more efficient use of RAM. Images are constructed from layered filesystems so they can share common files, making disk usage and image downloads much more efficient.

Docker containers are based on open standards allowing containers to run on all major Linux distributions.

## Docker Terminology

* Docker engine: The process running on your production machine that sits between your running Docker application and the underlying operating system and hardware.
* Dockerfile: A text file that contains the instructions (or commands) used to build a Docker image.
* Docker image: The result of building a Dockerfile and executing the Dockerfile's commands. It is constructed from a root operating system, installed applications, and commands executed in such a way that it can run your application. A Docker image serves as the basis for Docker containers and is the static template from which they are created.
* Docker container: A runtime instance of a Docker image. Whereas the Docker image is like a template (built from a Dockerfile that contains the correct operating system, applications, and commands used to build the image), the container is an actual running instance of that image.
* Docker host: A physical or virtual machine that runs the Docker engine to host your Docker container's DockerHub.
* DockerHub: The repository that hosts Docker images. Think of a DockerHub being to Docker images what GitHub is to Git repositories: a central location for hosting and serving up Docker images.

See [here](http://www.javaworld.com/article/3000781/development-tools/open-source-java-projects-docker.html) for a good high level overview on Docker with focus on Java Development. 

## Docker Setup
The default size for a virtualbox created through docker-machine is not large enough for simulating a small number 
of Java machines, this can easily be modified as below:

    docker-machine create --virtualbox-disk-size 40000 --driver virtualbox default 
    
Remember to learn the new settings after making this change.

    eval "$(docker-machine env default)" 
    
To create the base image for use by the software cd into [docker/dockerfiles/oraclejdk8](../docker/dockerfiles/oraclejdk8) and run the following command

    docker build -t oraclejdk8 . 

## Useful Docker commands
To get a list of installed images:

    docker ps -a 
    
Docker allows us to restart a container with a single command:

    docker restart dockergradlespringmariadb_backend_1
    
In case we want to destroy a container, perhaps because the image does not suit our needs, we can stop it and then run:

    docker rm dockergradlespringmariadb_backend_1
    
A container can also be frozen with the pause command. Docker will freeze the process using croups. The container will not know that it is being frozen and, when we unpause it, the container will resume its work as expected.

    docker pause dockergradlespringmariadb_backend_1
    docker unpause dockergradlespringmariadb_backend_1 
    
Pausing a container is very useful when we need to temporarily free our system's resources. If the container is not crucial at this moment (for example, it is performing some batch work), we can free it to allow other programs to run faster.

If the container doesn't start, or is not working properly, we can investigate with the following command:

    docker logs dockergradlespringmariadb_backend_1
    
To access the container via Bash, we will run this command:

    docker exec -it dockergradlespringmariadb_backend_1 bash 
    
Now we can use normal Linux commands like cd, ls, etc. We will have root privileges. We can even install software, for example:

    apt-get update; apt-get install vim
    