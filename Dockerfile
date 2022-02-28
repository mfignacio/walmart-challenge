FROM openjdk:8
EXPOSE 8080
ADD target/walmart-challenge-docker.jar walmart-challenge-docker.jar
ENTRYPOINT ["java", "-jar", "/walmart-challenge-docker.jar"]
