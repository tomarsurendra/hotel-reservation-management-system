FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 9004
ARG JAR_FILE=target/payment-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]