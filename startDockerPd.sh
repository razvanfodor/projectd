#!/bin/bash
mvn clean install
docker build -t raz/projectd .

#create projectD network
docker network remove pd_network
docker network create pd_network

#start mongodb
docker stop mongo
docker run -d --name=mongo --network=pd_network mongo || docker start mongo

#start projectD server container
docker stop projectd
docker rm projectd
docker run -d --name=projectd --network=pd_network -p 8080:8080  raz/projectd