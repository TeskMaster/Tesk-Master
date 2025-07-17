FROM openjdk:17-jdk-alpine
# O Dockerfile agora espera que o JAR esteja dentro da pasta 'target/'
ARG JAR_FILE=target/config-server-0.0.1-SNAPSHOT.jar # <--- Caminho atualizado para o JAR
COPY ${JAR_FILE} app.jar
EXPOSE 8888 
ENTRYPOINT ["java", "-jar", "/app.jar"]