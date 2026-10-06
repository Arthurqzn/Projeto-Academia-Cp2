# ==========================================================
# STAGE 1 - Build da aplicacao com Maven
# ==========================================================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache de dependencias do Docker
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copia o restante do codigo fonte e gera o artefato (.jar)
COPY src ./src
RUN mvn -B clean package -DskipTests

# ==========================================================
# STAGE 2 - Imagem final, somente com o JRE (imagem enxuta)
# ==========================================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia apenas o jar gerado no stage de build
COPY --from=build /app/target/*.jar app.jar

# Profile padrao de execucao (pode ser sobrescrito com -e SPRING_PROFILES_ACTIVE=prd)
ENV SPRING_PROFILES_ACTIVE=default

# Porta exposta pela aplicacao Spring Boot
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
