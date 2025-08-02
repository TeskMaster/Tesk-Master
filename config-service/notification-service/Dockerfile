# Usa imagem com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Instala Maven no ambiente
RUN apk add --no-cache maven

# Define o diretório base dentro do container
WORKDIR /app

# Copia os arquivos do serviço para o container
COPY . .

# Executa o build e gera o JAR (sem testes)
RUN mvn clean package -DskipTests

# Roda o JAR quando o container iniciar
CMD ["sh", "-c", "java -jar target/*.jar"]
