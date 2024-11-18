#!/bin/bash

# --network garden-network \

docker run \
  --name garden-eureka-server \
  --restart=on-failure \
  --detach \
  --env SPRING_PROFILES_ACTIVE=prod \
  --env EUREKA_USER="${EUREKA_USER}" \
  --env EUREKA_PASSWORD="${EUREKA_PASSWORD}" \
  --publish 8761:8761 \
  eureka-server