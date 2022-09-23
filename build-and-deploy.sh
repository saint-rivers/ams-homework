#!/bin/bash

docker-compose down
./gradlew build -x test
docker-compose \
  -f docker-compose.yml \
  -f docker-compose-elk.yml \
  up -d --build
