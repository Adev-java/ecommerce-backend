# Use Java 17
FROM openjdk:17-jdk-slim

# Copy project files
WORKDIR /app
COPY . .

# Build the project
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Run the jar file
CMD ["java", "-jar", "target/*.jar"]