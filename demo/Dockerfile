# 1. Use a lightweight Java runtime as the base
FROM eclipse-temurin:17-jre-alpine

# 2. Create a directory inside the container for our app
WORKDIR /app

# 3. Copy the 'fat jar' from your target folder into the container
# Note: The name must match what you see in your 'target' folder
COPY target/demo-0.0.1-SNAPSHOT.jar library.jar

# 4. Tell the container to run the jar file when it starts
ENTRYPOINT ["java", "-jar", "library.jar"]
