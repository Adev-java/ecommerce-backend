# Step 1: Build (Jar file banane ke liye)
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run (Sirf jar file chalane ke liye)
FROM eclipse-temurin:17-jdk
WORKDIR /app
# Yahan *.jar use karein taaki version change hone par bar-bar update na karna pade
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
