FROM openjdk:11-jdk-alpine
COPY "./target/nuvve-crud-0.0.1.jar" "app.jar"
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]