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

This project will create two containers, which are...

1. A PostgreSQL database container
2. A JDK container with the AMS API

Run the commands below to build and deploy the project.

Build the jar file:

```shell
make -B build
```

Deploy all relevant containers

```shell
make deploy
```

To stop and remove all containers, run:

```bash
make down
```
