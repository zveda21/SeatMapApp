FROM gradle:7.6.0-jdk17 as build
WORKDIR /app
COPY . .
RUN gradle build
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8086
# Run the jar file
CMD ["java", "-jar", "app.jar"]