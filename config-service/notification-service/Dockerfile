# Base com JDK 17 e Alpine para leveza
FROM eclipse-temurin:17-jdk-alpine

# Instala o Maven necessário para o build
RUN apk add --no-cache maven

# Define diretório interno
WORKDIR /app

# Copia o código-fonte para o container
COPY . .

# Build do serviço sem rodar testes
RUN mvn clean package -DskipTests

# Comando que roda o .jar
CMD ["sh", "-c", "java -jar target/*.jar"]
