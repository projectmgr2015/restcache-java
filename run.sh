#!/usr/bin/env bash
docker rm -f restcache-java-tomcat
docker run --name restcache-java-tomcat -p 8080:8080 -d restcache-java-tomcat