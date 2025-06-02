FROM maven:3.9.8-amazoncorretto-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/myapp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "myapp.jar"]