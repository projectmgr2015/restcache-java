FROM ubuntu:14.04

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update && \
    apt-get -y install software-properties-common && \
    add-apt-repository ppa:webupd8team/java && \
    apt-get update && \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get -y install oracle-java8-installer && \
    apt-get clean && \
    apt-get purge && \
    rm /var/cache/oracle-jdk8-installer/jdk-8u*-linux-x64.tar.gz && \
    update-alternatives --display java && \
    echo "JAVA_HOME=/usr/lib/jvm/java-8-oracle" >> /etc/environment

ADD . /app/

WORKDIR /app/
RUN ./gradlew build && \
    find / -name gradle-2.*-all.zip -delete

CMD ["java", "-jar", "build/libs/restcache-1.0.jar"]