# BeeStore Java — Spring Boot API

## Présentation du projet

BeeStore Java est une application backend développée avec **Spring Boot**. Ce projet sert également de support pratique pour découvrir les outils DevOps, notamment l'automatisation des tests, la mesure de la couverture de code et l'analyse de la qualité logicielle.

## Technologies utilisées

- **Java 17** — langage de programmation
- **Spring Boot 3.5.6** — framework backend
- **Maven** — gestion des dépendances et construction du projet
- **Spring Web** — développement d'API REST
- **Spring Data JPA** — accès aux données
- **H2 / MySQL** — bases de données prises en charge par les dépendances du projet
- **Swagger / OpenAPI** — documentation interactive de l'API
- **JaCoCo 0.8.11** — mesure de la couverture de code par les tests
- **SonarCloud** — configuration du projet pour l'analyse de la qualité du code
- **Spring Boot Actuator** — fonctionnalités de supervision de l'application

## Prérequis

Avant de lancer le projet, assurez-vous de disposer de :

- Java Development Kit (JDK) 17
- Maven, ou du Maven Wrapper si celui-ci est présent dans le dépôt
- Git
- IntelliJ IDEA ou un autre IDE compatible avec Java

Selon la configuration de la base de données, un serveur MySQL peut également être nécessaire.

## Installation et démarrage

### 1. Cloner le dépôt

```bash
git clone https://github.com/salcia100/beestore-java.git
cd beestore-java
```

### 2. Vérifier l'environnement

```bash
java -version
mvn -version
```

Vérifiez que Java 17 est utilisé. Si le projet contient un Maven Wrapper, vous pouvez utiliser `mvnw.cmd` sous Windows à la place de `mvn`.

### 3. Construire le projet et exécuter les tests

```bash
mvn clean verify
```

Cette commande nettoie les fichiers de construction précédents, compile le code, exécute les tests et lance les phases de vérification Maven configurées.

### 4. Lancer l'application

```bash
mvn spring-boot:run
```

Si la construction et la configuration de la base de données sont correctes, l'application démarre sur le port 8080, sauf configuration différente.

## Documentation de l'API

Une fois l'application démarrée, accédez à Swagger UI :

http://localhost:8080/swagger-ui/index.html

Swagger permet de consulter les endpoints documentés et, selon leur configuration, d'effectuer des requêtes HTTP directement depuis l'interface.

## Mesure de la couverture de code avec JaCoCo

JaCoCo est un outil qui mesure la partie du code exécutée pendant les tests automatisés. Il permet d'identifier les parties du code qui ne sont pas suffisamment couvertes par les tests.

Le plugin JaCoCo est configuré dans le fichier `pom.xml` avec la version `0.8.11`.

### Générer le rapport

Exécutez :

```bash
mvn clean verify
```

Après l'exécution, consultez le rapport HTML à l'emplacement suivant :

`target/site/jacoco/index.html`

Ouvrez ce fichier dans un navigateur pour consulter les statistiques de couverture.

Le rapport XML est également généré à l'emplacement suivant :

`target/site/jacoco/jacoco.xml`

Ce fichier peut être utilisé par des outils d'analyse de qualité tels que SonarCloud.

**Remarque :** la génération du rapport dépend de l'exécution effective des tests et de la configuration Maven. Une couverture élevée ne garantit pas l'absence de défauts.

### Seuils de couverture

La configuration JaCoCo contient un exemple de règle de vérification de couverture, actuellement commenté dans `pom.xml`. Aucun seuil minimum de couverture n'est donc imposé par cette règle dans la configuration actuelle.

## Analyse de la qualité du code

Le projet contient une configuration Maven pour SonarCloud dans `pom.xml`, notamment la clé du projet, l'organisation et le chemin du rapport XML JaCoCo.

La présence de cette configuration ne signifie pas qu'une analyse SonarCloud a déjà été exécutée. Une analyse effective nécessite notamment une configuration valide du projet et les paramètres d'authentification appropriés.

## Outils DevOps

Ce projet peut servir de base à la mise en place d'un processus d'intégration continue (CI), comprenant notamment :

- La compilation automatique avec Maven.
- L'exécution des tests automatisés.
- La génération du rapport de couverture avec JaCoCo.
- L'analyse de la qualité du code avec SonarCloud.
- L'automatisation de ces étapes avec un serveur CI/CD tel que Jenkins.

Ces étapes d'automatisation doivent être configurées séparément avant de pouvoir être considérées comme opérationnelles.

## Structure du projet

Le projet suit la structure standard d'une application Maven :

```text
beestore-java/
├── src/
│   ├── main/
│   │   ├── java/       # Code source Java
│   │   └── resources/  # Configuration et ressources
│   └── test/
│       └── java/       # Tests automatisés
├── pom.xml             # Dépendances et plugins Maven
└── README.md           # Documentation du projet
```

Les répertoires et fichiers générés pendant la construction, notamment `target/`, sont créés par Maven.

## Auteur et dépôt

Dépôt GitHub : https://github.com/salcia100/beestore-java

Ce projet est utilisé dans le cadre des travaux pratiques DevOps.