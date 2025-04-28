# Estágio de build
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd

# Baixe todas as dependências primeiro (cache layer)
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B

# Copie os arquivos fonte e compile
COPY src src
RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests

# Estágio de execução
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Adicione um usuário não-root para executar a aplicação
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copie o JAR compilado do estágio de build
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta da aplicação
EXPOSE 8081

# Configure as variáveis de ambiente para o banco de dados
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ByteStore
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]