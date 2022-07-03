FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/company-info-service.jar company-info-service.jar
ENTRYPOINT ["java","-jar","/company-info-service.jar"]