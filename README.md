# Article Management System

Spring Boot API made with Spring Data JPA, Kotlin and PostgreSQL. All endpoints are open and no security has been applied.

For testing and training purposes in Korea Software HRD Center.

## Deployment

Clone the project

```shell
git clone https://github.com/saint-rivers/ams-homework.git ams-kshrd
```

Change directory to the project.

```shell
cd ams-kshrd
```

Run the shell script to build a JAR file and deploy two docker containers.
1. A PostgreSQL database container
2. A JDK container with the AMS API

```shell
/bin/bash build-and-deploy.sh
```