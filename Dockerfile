FROM openjdk:17
ADD target/spring-inventory-docker.jar spring-inventory-docker.jar
ENTRYPOINT ["java", "-jar", "spring-inventory-docker.jar"]

