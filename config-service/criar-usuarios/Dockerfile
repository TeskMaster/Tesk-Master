FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/criar-usuarios-0.0.1-SNAPSHOT.jar # <--- Caminho atualizado para o JAR
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]