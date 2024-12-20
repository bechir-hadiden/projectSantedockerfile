# Utiliser une image de base Java
FROM openjdk:17-jdk-slim

# Copier le fichier .jar du répertoire target dans l'image
COPY target/projetsante.jar projetsante.jar

# Définir le point d'entrée pour l'application
ENTRYPOINT ["java", "-jar", "projetsante.jar"]
