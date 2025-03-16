FROM maven:3.9.9-sapmachine-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src/ src/

RUN mvn clean package -DskipTests

FROM sapmachine:17-jre-headless-ubuntu-focal

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY data/output-peaks.json /app/data/output-peaks.json

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
