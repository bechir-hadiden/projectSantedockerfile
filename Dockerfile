FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -Dmaven.test.skip=true
# Utiliser une image de base Java
FROM openjdk:17-jdk-slim

# Copier le fichier .jar du répertoire target dans l'image
COPY target/projetsante.jar projetsante-0.0.1-SNAPSHOT.jar

RUN mvn clean install -Dmaven.test.skip=true

# Définir le point d'entrée pour l'application
ENTRYPOINT ["java", "-jar", "projetsante-0.0.1-SNAPSHOT.jar"]
