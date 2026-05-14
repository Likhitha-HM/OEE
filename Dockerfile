FROM eclipse-temurin:17-jdk-alpine
COPY target/demo-app8-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
