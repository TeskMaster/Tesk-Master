# Etapa 1: build com Maven
FROM eclipse-temurin:17-jdk-alpine as builder

# Instalar Maven
RUN apk add --no-cache maven

WORKDIR /build

# Copiar todo o projeto pai
COPY pom.xml .
COPY config-server ./config-server
COPY discovery-server ./discovery-server

# Fazer build do projeto inteiro
RUN cd discovery-server && mvn clean package

# Etapa 2: runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o JAR do discovery-server
COPY --from=builder /build/discovery-server/target/app.jar app.jar

CMD ["java", "-jar", "app.jar"]
