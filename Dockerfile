FROM eclipse-temurin:21.0.5_11-jdk-alpine
RUN mkdir /opt/app
COPY target/*.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]