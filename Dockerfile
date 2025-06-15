FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

# ✅ Tambahkan izin eksekusi ke mvnw
RUN chmod +x mvnw

# Build dependencies offline
RUN ./mvnw dependency:go-offline

# Build project
RUN ./mvnw clean install -DskipTests

# Use a smaller image for the final jar
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]