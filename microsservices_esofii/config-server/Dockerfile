# Usa uma imagem leve com JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Instala Maven dentro da imagem
RUN apk add --no-cache maven

# Define o diretório de trabalho
WORKDIR /app

# Copia todos os arquivos da aplicação para dentro da imagem
COPY . .

# Executa o build sem rodar os testes
RUN mvn clean package -DskipTests

# Comando que será executado quando o container iniciar
CMD ["sh", "-c", "java -jar target/*.jar"]
