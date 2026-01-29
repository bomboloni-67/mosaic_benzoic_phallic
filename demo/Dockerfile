# --- STAGE 1: Build the Application ---
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app

# 1. Copy only the pom.xml first to cache dependencies (speeds up future builds)
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copy the source code and build the fat jar
COPY src ./src
RUN mvn clean package -DskipTests

# --- STAGE 2: Run the Application ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 3. Copy the jar from the "build" stage to this final image
# Note: Use a wildcard (*) if you're not sure of the exact filename
COPY --from=build /app/target/*.jar library.jar

# 4. Expose the port your app runs on (Render uses this to route traffic)
EXPOSE 8080

# 5. Optimized entrypoint for cloud environments
ENTRYPOINT ["java", "-Xmx512m", "-jar", "library.jar"]