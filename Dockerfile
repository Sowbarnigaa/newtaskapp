FROM openjdk:21
ADD MyApp/target/myapptask.jar myapptask.jar
ENTRYPOINT ["java", "-jar", "/myapptask.jar"]