#!/usr/bin/env bash
docker build -t weather-api .
docker run -p 8080:8080 -it \
       -v "$(pwd)":/usr/src/app -v ~/.m2:/root/.m2 \
       weather-api
#sh exec.sh