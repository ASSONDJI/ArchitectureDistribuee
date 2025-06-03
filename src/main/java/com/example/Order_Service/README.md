#  PayStock – Order Service

`Order-Service` est un microservice Java Spring Boot faisant partie de la plateforme **PayStock**, dédiée à la gestion distribuée des commandes, clients et produits dans un environnement e-commerce moderne.

Ce service gère la **création, la mise à jour, la suppression et la consultation des commandes**, tout en interagissant avec d’autres microservices comme `Customer-Service`, `Product-Service` et `Bill-Service`.

---

## Objectifs du service

Le microservice `Order-Service` répond aux besoins suivants :

- Créer une commande avec ses produits associés.
- Récupérer toutes les commandes avec les informations complètes du client et de la facture.
- Mettre à jour une commande existante.
- Supprimer une commande.
- Communiquer avec `Customer-Service`, `Product-Service` et `Bill-Service` via **OpenFeign**.
- S'enregistrer et être découvert dynamiquement grâce à **Eureka**.

---

##  Stack technique

- **Langage :** Java 17
- **Framework :** Spring Boot 3.x
- **Base de données :** PostgreSQL
- **Architecture :** Microservices
- **Découverte de services :** Eureka
- **Passerelle API :** Spring Cloud Gateway
- **Sécurité :** Spring Security + JWT
- **Documentation API :** Swagger (OpenAPI 3)
- **Tests :** JUnit 5, Mockito, MockMvc

---

##  Endpoints disponibles via la Gateway

| Méthode | URI                  | Description                            |
|--------|----------------------|----------------------------------------|
| POST   | /order/create        | Création d’une commande                |
| GET    | /order/all           | Récupération de toutes les commandes   |
| GET    | /order/{id}          | Détails d’une commande spécifique      |
| PUT    | /order/{id}          | Mise à jour d’une commande             |
| DELETE | /order/{id}          | Suppression d’une commande             |

> Tous les endpoints sont accessibles uniquement via le **gateway-service** après **authentification**.

---

##  Sécurité et authentification

Le service applique une **politique de sécurité basée sur JWT (JSON Web Tokens)** :

- Chaque requête est authentifiée à l’aide d’un token JWT valide.
- La vérification de l’authentification et des autorisations est assurée par la **gateway**.

---

## Configuration standard (application.properties)

```properties
spring.application.name=Order-Service
server.port=8582

# Base de données PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/orderservice
spring.datasource.username=postgres
spring.datasource.password=L

# JPA / Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Eureka Client
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
