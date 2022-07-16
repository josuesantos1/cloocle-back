FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/cloocle-back-0.0.1-SNAPSHOT-standalone.jar /cloocle-back/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/cloocle-back/app.jar"]
