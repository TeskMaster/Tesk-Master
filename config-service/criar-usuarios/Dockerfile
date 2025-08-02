# Imagem base com JDK 17 e Alpine Linux
FROM eclipse-temurin:17-jdk-alpine

# Instala Maven
RUN apk add --no-cache maven

# Define a pasta de trabalho do container
WORKDIR /app

# Copia os arquivos do microserviço
COPY . .

# Compila o projeto e gera o .jar
RUN mvn clean package -DskipTests

# Inicia o microserviço executando o jar
CMD ["sh", "-c", "java -jar target/*.jar"]
