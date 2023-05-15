#
# Bild stage
#
FROM maven:3.9.1-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -pl bot -am -Dmaven.test.skip

#
# Package stage
#
FROM eclipse-temurin:17-alpine
COPY --from=build bot/target/bot-*.jar bot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bot.jar"]
