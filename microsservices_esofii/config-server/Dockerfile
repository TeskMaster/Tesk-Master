# Etapa 1: build com Maven
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /build

# Copiar todo o projeto pai
COPY pom.xml .
COPY config-server ./config-server
COPY discovery-server ./discovery-server

# Dar permissão de execução para o Maven Wrapper
RUN chmod +x config-server/mvnw

# Fazer build do projeto inteiro
RUN cd config-server && ./mvnw clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o JAR do config-server
COPY --from=builder /build/config-server/target/app.jar app.jar

CMD ["java", "-jar", "app.jar"]
