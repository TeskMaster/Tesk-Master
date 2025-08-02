# Usa imagem leve com JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Instala Maven
RUN apk add --no-cache maven

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do serviço
COPY . .

# Executa o build com Maven
RUN mvn clean package -DskipTests

# Executa o JAR gerado na imagem
CMD ["sh", "-c", "java -jar target/*.jar"]
