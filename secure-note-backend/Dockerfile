FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM eclipse-temurin:21-jdk as build
#
#WORKDIR /app
#
#COPY pom.xml .
#
#COPY src ./src
#
#RUN mvn clean package -DskipTests
#
#FROM eclipse-temurin:21-jdk
#
#WORKDIR /app
#
#COPY --from=build /app/target/secure-note-0.0.1-SNAPSHOT.jar /app/secure-note-0.0.1-SNAPSHOT.jar
#
#ENTRYPOINT ["java", "-jar", "/app/secure-note-0.0.1-SNAPSHOT.jar"]