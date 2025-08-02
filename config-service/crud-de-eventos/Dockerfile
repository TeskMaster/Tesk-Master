# Java 17 com base leve (Alpine)
FROM eclipse-temurin:17-jdk-alpine

# Instala Maven
RUN apk add --no-cache maven

# Define o diretório onde os comandos serão executados
WORKDIR /app

# Copia todos os arquivos do projeto para o container
COPY . .

# Compila o projeto, sem rodar testes
RUN mvn clean package -DskipTests

# Inicia a aplicação usando o JAR gerado
CMD ["sh", "-c", "java -jar target/*.jar"]
