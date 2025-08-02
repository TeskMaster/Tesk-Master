# Usa imagem base com JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Instala Maven
RUN apk add --no-cache maven

# Define o diretório de trabalho
WORKDIR /app

# Copia todos os arquivos do serviço para a imagem
COPY . .

# Executa o build (sem testes)
RUN mvn clean package -DskipTests

# Inicia o .jar gerado
CMD ["sh", "-c", "java -jar target/*.jar"]
