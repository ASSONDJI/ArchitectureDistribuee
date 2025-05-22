

# RealTimeTasker – Bill Service

Bill Service est un microservice développé en Java avec Spring Boot. Il s'intègre dans la plateforme distribuée RealTimeTasker, dédiée à la gestion collaborative de projets et de tâches en temps réel.

Ce service assure la gestion des factures et leur mise à jour dans l’écosystème distribué de la plateforme.



## Objectifs du service

Ce module a été conçu pour répondre aux besoins suivants :

- Enregistrer de nouvelles Factures.
- Supprimer une Facture.
- Récupérer la liste des Facture existants.
- Offrir une communication fluide avec les autres microservices via Eureka et OpenFeign



## Stack technique

- Langage : Java 17
- Framework : Spring Boot 3.x
- Base de données : PostgreSQL
- Architecture : Microservices
- Découverte de services : Eureka
- Passerelle d’API : Spring Cloud Gateway
- Sécurité : Spring Security + JWT
- Documentation API : Swagger (OpenAPI 3)
- Tests : JUnit 5, Mockito, Postman

---

## Endpoints disponibles via la Gateway

| Méthode | URI               | Description                  |
|--------|-------------------|------------------------------|
| POST   | /bill/save        | Création d’une facture       |
| GET    | /bill/get         | Liste de toutes les factures |
| PUT    | /bill/update/{id} | Mise à jour d’une facture    |
| DELETE | /bill/delete/{id} | Suppression d’une facture    |

> Tous les endpoints sont accessibles uniquement via le Gateway (gateway-service) après authentification.



## Sécurité et gestion des accès

Le bill-service applique une politique de sécurité basée sur JWT (JSON Web Tokens) :

- Chaque requête est authentifiée par un token JWT valide.
- La vérification se fait en amont par le Gateway.


## Configuration standard (application.properties)

spring.application.name=Bill-Service
spring.datasource.url=jdbc:postgresql://localhost:5432/BillService
spring.datasource.username=postgres
spring.datasource.password=L
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto = update
server.port =8581
eureka.client.service-url.defaultZone=http://localhost:8761/eureka