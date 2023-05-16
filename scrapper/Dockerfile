FROM eclipse-temurin:20-jre-alpine
COPY target/scrapper-*.jar scrapper.jar
EXPOSE 8080
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "scrapper.jar"]
