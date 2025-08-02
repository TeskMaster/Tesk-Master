# Etapa 1: build com Maven
FROM eclipse-temurin:17-jdk-alpine as builder

RUN apk add --no-cache maven

WORKDIR /build

# Copia todo o conteúdo da pasta config-server
COPY . .

# Build do projeto diretamente
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o JAR do build
COPY --from=builder /build/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
