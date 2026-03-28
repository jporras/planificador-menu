FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY pom.xml ./
COPY src ./src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

ENV QUARKUS_PROFILE=prod

COPY --from=build /workspace/target/quarkus-app/ /app/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/quarkus-run.jar"]
