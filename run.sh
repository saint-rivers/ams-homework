#!/bin/bash

./gradlew bootJar -x test && docker compose up -d --build