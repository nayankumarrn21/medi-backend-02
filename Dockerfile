FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY ./target/mediassistspolicyservice-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "mediassistspolicyservice-0.0.1-SNAPSHOT.jar"]
