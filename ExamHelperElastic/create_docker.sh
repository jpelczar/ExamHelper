#!/usr/bin/env bash
docker rmi docker.elastic.co/elasticsearch/elasticsearch:5.5.0
docker pull docker.elastic.co/elasticsearch/elasticsearch:5.5.0