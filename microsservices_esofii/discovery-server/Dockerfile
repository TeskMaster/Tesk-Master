# Imagem base com JDK 17 e Alpine Linux
FROM eclipse-temurin:17-jdk-alpine

# Instala o Maven dentro do container
RUN apk add --no-cache maven

# Define o diretório de trabalho
WORKDIR /app

# Copia todos os arquivos do microserviço para dentro do container
COPY . .

# Realiza o build com Maven (sem executar os testes)
RUN mvn clean package -DskipTests

# Executa o JAR gerado
CMD ["sh", "-c", "java -jar target/*.jar"]
