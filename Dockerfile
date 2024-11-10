FROM openjdk:17
EXPOSE 8085
ADD target/Blog-amine-0.0.1-SNAPSHOT.jar micro-docker.jar
ENTRYPOINT ["java", "-jar", "micro-docker.jar"]