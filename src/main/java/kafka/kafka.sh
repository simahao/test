#!/bin/bash

container_id=$(docker ps -q -f "name=^kafka$")

if [[ ${container_id} == "" ]]; then
    echo "pls start kafka"
    exit 1
else
    if [[ $1 == "list" ]]; then
        docker exec -it ${container_id} ls -al /opt/bitnami/kafka/bin
    else
        docker exec -it ${container_id} /opt/bitnami/kafka/bin/$@
    fi
fi

