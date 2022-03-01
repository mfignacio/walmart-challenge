FROM openjdk:8
ADD target/walmart-challenge-docker.jar walmart-challenge-docker.jar
ENTRYPOINT ["java", "-jar", "/walmart-challenge-docker.jar"]
