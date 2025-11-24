FROM maven:3.9.6-eclipse-temurin-17 AS build


WORKDIR /app


COPY pom.xml .


RUN mvn dependency:go-offline


COPY src ./src


RUN mvn package -DskipTests


FROM eclipse-temurin:17-jre-alpine


ARG JAR_FILE=/app/target/*.jar


COPY --from=build ${JAR_FILE} app.jar


EXPOSE 8080


CMD java -jar app.jar \
  --logistica-gateway.url-camiones=${LOGISTICA_GATEWAY_URL_CAMIONES:-http://localhost:8081} \
  --logistica-gateway.url-contenedores=${LOGISTICA_GATEWAY_URL_CONTENEDORES:-http://localhost:8082} \
  --logistica-gateway.url-solicitudes=${LOGISTICA_GATEWAY_URL_SOLICITUDES:-http://localhost:8083} \
  --logistica-gateway.url-tarifas=${LOGISTICA_GATEWAY_URL_TARIFAS:-http://localhost:8084} \
  --logistica-gateway.url-rutas=${LOGISTICA_GATEWAY_URL_RUTAS:-http://localhost:8085} \
  --logistica-gateway.url-usuarios=${LOGISTICA_GATEWAY_URL_USUARIOS:-http://localhost:8086} \
  --logistica-gateway.url-maps=${LOGISTICA_GATEWAY_URL_MAPS:-http://localhost:8087} \
  --spring.security.oauth2.resourceserver.jwt.issuer-uri=${SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI:-http://localhost:8091/realms/logistica}
