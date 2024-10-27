FROM maven:3.9.9-eclipse-temurin-21-jammy AS builder
# Set the working directory
WORKDIR /app

# Copy only the Maven wrapper and the pom.xml first
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /app/target/bankingapp-0.0.1-SNAPSHOT.jar .
# Expose the application port
EXPOSE 3000

# Run the application
CMD ["java", "-jar", "bankingapp-0.0.1-SNAPSHOT.jar"]
