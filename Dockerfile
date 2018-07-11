FROM openjdk:8-jdk-alpine

ADD target/verzekeren-backend-*.jar verzekeren-backend.jar
ADD docker/application.properties application.properties

ENTRYPOINT [ "sh", "-c", "java -jar /verzekeren-backend.jar --spring.config.location=/application.properties"]