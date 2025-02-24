# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project JAR file into the container at /app
COPY 17-myProject/thymeleafdemo/target/thymeleafdemo-*.jar app.jar

# Copy the SQLite database file into the container at /app
COPY 17-myProject/Db/hotel_db.db /app/Db/hotel_db.db

# Expose the port that the application will run on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]