

# Stage 1: Build the app
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY mvnw .
COPY .mvn/ .mvn
COPY pom.xml ./
COPY src ./src

RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:21-jdk

WORKDIR /app
# 👇 change *.jar → *.war
COPY --from=builder /app/target/*.war app.war

# expose the port (same as your Spring Boot app)
EXPOSE 8085

# 👇 run the WAR file instead of JAR
ENTRYPOINT ["java", "-jar", "app.war"]
