FROM eclipse-temurin:20-jre-alpine
COPY target/bot-*.jar bot.jar
EXPOSE 8081
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "bot.jar"]
