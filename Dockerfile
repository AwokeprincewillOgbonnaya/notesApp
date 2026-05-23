FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY notesapp/.mvn/ .mvn
COPY notesapp/mvnw notesapp/pom.xml ./
RUN ./mvnw dependency:go-offline
COPY notesapp/src ./src
RUN ./mvnw clean package -DskipTests
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "target/notesapp-0.0.1-SNAPSHOT.jar"]