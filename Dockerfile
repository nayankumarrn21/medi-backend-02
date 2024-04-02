FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY ./target/MediAssistsPolicyService-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "MediAssistsPolicyService-0.0.1-SNAPSHOT.jar"]