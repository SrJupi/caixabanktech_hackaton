FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy only the Maven wrapper and the pom.xml first
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execute permission to the Maven wrapper
RUN chmod +x mvnw

# Install dependencies (this layer will be cached)
RUN ./mvnw dependency:go-offline

# Now copy the rest of the application code
COPY src ./src

# Build the application (this layer will be rebuilt only when src changes)
RUN ./mvnw install -DskipTests -Dpmd.skip=true

# Expose the application port
EXPOSE 3000

# Run the application
CMD ["java", "-jar", "target/bankingapp-0.0.1-SNAPSHOT.jar"]
