#
# Bild stage
#
FROM maven:3.9.1-eclipse-temurin-17 AS build

COPY scrapper/src scrapper/src
COPY scrapper/pom.xml scrapper

COPY link-parser/src link-parser/src
COPY link-parser/pom.xml link-parser

COPY scrapper-jooq/src scrapper-jooq/src
COPY scrapper-jooq/pom.xml scrapper-jooq

COPY bot/src bot/src
COPY bot/pom.xml bot

COPY pom.xml .
RUN mvn clean package -pl scrapper -am

#
# Package stage
#
FROM eclipse-temurin:17-alpine
COPY --from=build /home/app/scrapper/target/scrapper-*.jar /usr/local/lib/scrapper.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/scrapper.jar"]
