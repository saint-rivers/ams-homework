#!/bin/bash

IMG_NAME=saintrivers/ams-api
IMG_VERSION=2.1.4

docker build -f JRE.Dockerfile .
docker push ${IMG_NAME}:${IMG_VERSION}
