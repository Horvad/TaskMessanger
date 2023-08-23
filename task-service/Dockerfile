FROM eclipse-temurin:19-jre-focal
MAINTAINER ITAcademy

ADD ./target/task-service-1.0-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/task-service-1.0-SNAPSHOT.jar"]

EXPOSE 8080 8089