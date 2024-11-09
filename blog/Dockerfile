FROM openjdk:17
EXPOSE 8085
ADD target/blog-1.0-SNAPSHOT.jar micro-docker.jar
ENTRYPOINT ["java", "-jar", "micro-docker.jar"]