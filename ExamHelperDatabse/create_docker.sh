#!/bin/bash

docker rmi sameersbn/mysql
docker build -t sameersbn/mysql .
