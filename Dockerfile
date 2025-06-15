FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline

COPY . .

RUN ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/*.jar"]
