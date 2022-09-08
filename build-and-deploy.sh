#!/bin/bash

docker-compose down
./gradlew build -x test
docker-compose up -d --build