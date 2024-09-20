FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/vip_rent-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]