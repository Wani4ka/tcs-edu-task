#
# Bild stage
#
FROM maven:3.9.1-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -pl scrapper -am -Dmaven.test.skip

#
# Package stage
#
FROM eclipse-temurin:17-alpine
COPY --from=build scrapper/target/scrapper-*.jar scrapper.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "scrapper.jar"]
