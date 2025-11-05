# ===========================
# Stage 1: Build the app
# ===========================
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy Maven Wrapper files
COPY mvnw .
COPY .mvn/ .mvn
COPY pom.xml .
COPY src ./src

# ✅ Fix permission issue
RUN chmod +x mvnw

# Build the WAR (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# ===========================
# Stage 2: Run the app
# ===========================
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the WAR file from the builder stage
COPY --from=builder /app/target/*.war app.war

# Expose the app port
EXPOSE 8085

# Run the Spring Boot WAR file
ENTRYPOINT ["java", "-jar", "app.war"]
