# library
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE.txt)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/IQPlay/library)](https://github.com/IQPlay/library/releases)

[![Build & test](https://github.com/IQPlay/library/actions/workflows/gradle-build-test.yml/badge.svg?branch=main)](https://github.com/IQPlay/library/actions/workflows/gradle-build-test.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
<br/>
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=bugs)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=coverage)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![codecov](https://codecov.io/github/IQPlay/library/graph/badge.svg?token=vT0cGItXEP)](https://codecov.io/github/IQPlay/library)

## À propos du projet

IQPlayLib est une bibliothèque Java conçue pour simplifier la création de jeux vidéo interactifs. Elle fournit des outils prêts à l'emploi pour gérer des sessions de jeu, des niveaux, des scores et des questions, permettant ainsi aux développeurs de se concentrer sur la logique et le design de leurs jeux. Que vous souhaitiez créer un quiz, un jeu éducatif ou un jeu basé sur des défis, IQPlayLib vous offre une base modulaire pour démarrer rapidement.

### Qui sommes-nous ?

Nous sommes deux étudiants en master MIAGE (Méthodes Informatiques Appliquées à la Gestion des Entreprises) à l'Université de Paris Nanterre.
Nous avons créé cette bibliothèque dans le cadre de notre projet universitaire, pour aider les développeurs à concevoir des jeux plus facilement tout en expérimentant avec des concepts avancés de programmation Java.

---

## Prise en main

### Prérequis

Assurez-vous que les éléments suivants sont installés sur votre système :

- Java 21 ou une version supérieure
- Gradle 8.10.2 ou une version supérieure

### 1. Configurer les identifiants GitHub

Le package nécessite une authentification. Ajoutez vos identifiants GitHub dans un fichier `gradle.properties` situé dans votre répertoire utilisateur `~/.gradle/gradle.properties` :

```bash
gpr.user=YourGitHubUsername
gpr.key=YourGitHubToken
```

Remarque : Assurez-vous que votre token GitHub possède la permission read:packages pour accéder aux packages.

### 2. Ajouter la dépendance
   Dans votre fichier build.gradle, ajoutez la dépendance pour [IQPlayLib](https://github.com/IQPlay/library/packages/2304124):

```gradle
dependencies {
    implementation 'fr.parisnanterre:iqplaylib:0.2'
}
```

```gradle
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/IQPlay/library/")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
    }
}
```

### 3. Exécuter l'application
Utilisez le wrapper Gradle pour construire le projet et vérifier que tout est correctement configuré :
```bash
./gradlew clean build
```

## Exemple d'utilisation
Pour créer un jeu de quiz personnalisé en utilisant IQPlayLib, vous pouvez commencer par étendre la classe `AbstractGame`. Voici un exemple basique :

```java

import fr.parisNanterre.iqplaylib.core.*;

public class CustomGame extends AbstractGame {

    public CustomGame(String name) {
        super(name);
    }

    @Override
    protected IGameSession createGameSession() {
        return new CustomGameSession(this);
    }
}
```

## Aperçu sur Youtube
<a href="https://youtu.be/kt18qsJTzoQ">
  <img src="https://i.ibb.co/hyLmY7T/Capture-d-cran-2024-11-08-20-39-30.png" width="200" />
</a>