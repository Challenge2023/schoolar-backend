FROM openjdk:17-jre-slim

COPY target/schoolarAPI-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]