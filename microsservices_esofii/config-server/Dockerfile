# Imagem base com JDK 17 e Alpine Linux
FROM eclipse-temurin:17-jdk-alpine

# Instala o Maven no container
RUN apk add --no-cache maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o conteúdo do microserviço para o container
COPY . .

# Build com Maven (sem testes)
RUN mvn clean package -DskipTests

# Executa o .jar gerado após o build
CMD ["sh", "-c", "java -jar target/*.jar"]
