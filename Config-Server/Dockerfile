FROM openjdk:8
EXPOSE 8888
ADD target/config-server-docker.jar config-server-docker.jar
ENTRYPOINT ["java","-jar","/config-server-docker.jar"]