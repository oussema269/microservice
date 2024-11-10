# Utilise une image de base contenant Java 17
FROM openjdk:17

# Expose le port sur lequel votre application Spring Boot s'exécute
EXPOSE 8080

# Copie le fichier .jar généré dans le conteneur
ADD target/Eureuka-0.0.1-SNAPSHOT.jar eureuka.jar

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "eureuka.jar"]
