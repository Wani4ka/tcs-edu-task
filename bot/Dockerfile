FROM eclipse-temurin:17-alpine
COPY target/bot-*.jar bot.jar
EXPOSE 8081
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "bot.jar"]
