# ED-156-SA: later remove -DskipTests

FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk-21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=dev

EXPOSE 8282
ENTRYPOINT ["java","-jar","app.jar"]