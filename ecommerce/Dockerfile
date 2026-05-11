# Step 1: Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Sabse pehle poora code copy karein
COPY . .

# Ab 'ecommerce' folder ke andar jaakar build karein kyunki pom.xml wahi hai
WORKDIR /app/ecommerce
RUN mvn clean package -DskipTests

# Step 2: Runtime stage
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Build stage se jar file uthayein (path dhyan se dekhein)
COPY --from=build /app/ecommerce/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
