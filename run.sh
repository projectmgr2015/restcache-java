#!/usr/bin/env bash
docker rm -f restcache-java-jetty
docker run --name restcache-java-jetty -p 8080:8080 -d restcache-java-jetty