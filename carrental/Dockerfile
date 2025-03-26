# Utilise une image avec Java 17
FROM eclipse-temurin:17-jdk-jammy

# Dossier de travail dans le conteneur
WORKDIR /app

# Copie le fichier JAR généré par Gradle
COPY build/libs/*.jar app.jar

# Port exposé (remplace 8080 si ton app utilise un autre port)
EXPOSE 8080

# Commande de lancement
ENTRYPOINT ["java", "-jar", "app.jar"]