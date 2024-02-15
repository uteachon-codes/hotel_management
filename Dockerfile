FROM eclipse-temurin:17
EXPOSE 8080
COPY target/hotel_management-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]