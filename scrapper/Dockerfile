FROM eclipse-temurin:17-alpine
COPY target/scrapper-*.jar scrapper.jar
EXPOSE 8080
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "scrapper.jar"]
