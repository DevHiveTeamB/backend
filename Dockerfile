# java 17
FROM openjdk:17-jdk-alpine

COPY ./build/libs/devhive03-0.0.8.0.jar .

CMD ["java", "-jar", "devhive03-0.0.8.0.jar"]