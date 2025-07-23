FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia todos os arquivos do projeto para dentro da imagem
COPY . .

# Executa o build com Maven (usando o wrapper incluído no projeto)
RUN ./mvnw clean package

# Executa o .jar gerado (permitindo wildcard)
CMD ["sh", "-c", "java -jar target/*.jar"]