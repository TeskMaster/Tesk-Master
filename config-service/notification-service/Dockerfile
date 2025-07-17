# Etapa base: imagem do Java
FROM openjdk:17-jdk-alpine

# Nome do arquivo JAR (ajuste se o nome for diferente)
# O Dockerfile agora espera que o JAR esteja dentro da pasta 'target/'
ARG JAR_FILE=target/notification-service-0.0.1-SNAPSHOT.jar # <--- Caminho atualizado para o JAR

# Copia o JAR para dentro da imagem Docker
COPY ${JAR_FILE} app.jar

# Expõe a porta padrão do Spring Boot (geralmente 8080)
EXPOSE 8080 

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]