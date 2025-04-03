# Stage 1: Build the application using Gradle
FROM gradle:8.13.0-jdk21 AS build

# Set the working directory inside the container
WORKDIR /home/gradle/project

# Copy the Gradle wrapper and project files
COPY gradlew settings.gradle build.gradle /home/gradle/project/
COPY gradle /home/gradle/project/gradle

# Copy the source code into the container
COPY src /home/gradle/project/src

# Run the Gradle build to create the JAR file
RUN ./gradlew build --no-daemon

# Stage 2: Run the application
FROM openjdk:21 AS runtime

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
