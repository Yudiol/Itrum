
FROM openjdk:17.0.2-jdk-slim-buster
ENV SPRING_PROFILES_ACTIVE=production
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/application.properties /app/application.properties
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.properties"]
