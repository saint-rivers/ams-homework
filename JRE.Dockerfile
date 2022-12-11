FROM eclipse-temurin:17.0.3_7-jre-alpine
COPY build/libs/*.jar /opt/root.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/root.jar"]