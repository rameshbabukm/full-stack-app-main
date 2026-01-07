FROM maven:3.9-eclipse-temurin-17 as build

WORKDIR /task-service

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /task-service

COPY --from=build /task-service/target/*.jar task-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "task-service.jar"]
