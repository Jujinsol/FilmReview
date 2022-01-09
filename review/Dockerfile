FROM openjdk:11-jre-slim
ARG JAR_FILE=target/*.jar
COPY build/libs/*.jar application.jar

EXPOSE 8123

ENTRYPOINT ["java","-jar","/app.jar"]