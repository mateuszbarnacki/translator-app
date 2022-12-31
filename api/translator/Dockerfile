FROM maven:3.8.4-openjdk-17-slim AS build
COPY /src /src
COPY pom.xml pom.xml
RUN mvn -f ./pom.xml clean install

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/translator-app.jar translator-app.jar
ENTRYPOINT ["java", "-jar", "/translator-app.jar"]