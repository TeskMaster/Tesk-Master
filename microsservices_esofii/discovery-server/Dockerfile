# Etapa 1: Build com Maven
FROM eclipse-temurin:17-jdk-alpine as builder

RUN apk add --no-cache maven

WORKDIR /build

# Copia somente o conteúdo do discovery-server
COPY . .

RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o .jar gerado da etapa de build
COPY --from=builder /build/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
