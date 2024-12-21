# Étape 1 : Construction de l'application Maven
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -Dmaven.test.skip=true

# Étape 2 : Exécution de l'application avec une image Java légère
FROM openjdk:17-jdk-slim
WORKDIR /home/app

# Vérifiez que le nom du fichier est correct ici
COPY --from=build /home/app/target/projetsante-0.0.1-SNAPSHOT.jar projetsante-0.0.1-SNAPSHOT.jar

# Exposer le port utilisé par l'application
EXPOSE 8089

# Point d'entrée
ENTRYPOINT ["java", "-jar", "projetsante-0.0.1-SNAPSHOT.jar"]
