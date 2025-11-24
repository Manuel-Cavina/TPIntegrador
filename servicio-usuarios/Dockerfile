FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE=/app/target/*.jar

COPY --from=build ${JAR_FILE} app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]