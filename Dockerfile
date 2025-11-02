# Use a lightweight Java image as the base
FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/*.jar

# Install MySQL client for the wait script
RUN apt-get update && apt-get install -y mysql-client && rm -rf /var/lib/apt/lists/*

# Copy the JAR file from the build context to the container
COPY ./target/to-do-list-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-mysql.sh wait-for-mysql.sh
# Expose the port Spring Boot runs on
EXPOSE 8080

RUN chmod +x wait-for-mysql.sh

CMD ["./wait-for-mysql.sh"]

# Run the jar
#ENTRYPOINT ["java", "-jar", "app.jar"]