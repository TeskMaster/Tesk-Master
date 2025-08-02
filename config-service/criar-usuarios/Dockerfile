# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Instala Maven para o build
RUN apk add --no-cache maven

# Define o diretório onde o build será feito
WORKDIR /app

# Copia todos os arquivos do microserviço
COPY . .

# Faz o build do JAR sem executar testes
RUN mvn clean package -DskipTests

# Executa a aplicação
CMD ["sh", "-c", "java -jar target/*.jar"]
