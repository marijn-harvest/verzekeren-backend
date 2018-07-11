FROM openjdk:8-jdk-alpine

ADD target/verzekeren-backend-*.jar verzekeren-backend.jar

ENTRYPOINT [ "sh", "-c", "java -jar /verzekeren-backend.jar"]