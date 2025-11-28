# Edufy Pod
[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
## 🎙️Overview:
Edufy Pod handels different Podcast episodes and seasons. It isn't finished but can 
as of right now retrieve podcasts, play them and create new ones.
It's part of a bigger project and communicates with other microservices through 
docker compose. Other projects will be liked below
## 🧩 Related projects
### Organisation
- [EdufyProjects](https://github.com/EudfyProjects) - All repositories in one place
### Connections
- [Edify-infra](https://github.com/EudfyProjects/Edufy-infra) - Contains `docker-compose.yml` file and `init.db` file
- [EudfyEurekaServer](https://github.com/Sommar-skog/EdufyEurekaServer) - Server that connects the services instances
- [Gateway](https://github.com/SaraSnail/EdufyGateway) - Funnels all requests with one base endpoint
- [EdufyUser](https://github.com/Jamtgard/EdufyUser) - Holds in the Users and can connect to Keycloak to create new ones
- [EdufyKeycloak](https://github.com/Sommar-skog/EdufyKeycloak) - A pipeline for Azure but had to switch to local container 
### Media connections
- [EdufyCreator](https://github.com/Sommar-skog/EdufyCreator) - Holds the Creators for all the media services
- [EdufyGenre](https://github.com/a-westerberg/EdufyGenre) - Holds all the Genres for the microservices
- [EdufyThumb](https://github.com/a-westerberg/EdufyThumb) - Records of thumbs up and down on media
- [EdufyUtility](https://github.com/a-westerberg/EdufyUtility) - No code so far but was created to hold algorithms to extract top 10 for a User
### Media services
- [EdufyMusic](https://github.com/Jamtgard/EdufyMusic) - Service for songs and albums
- [EdufyVideo](https://github.com/Sommar-skog/EdufyVideo) - Service for video clips and video playlists
---

## 🚀 Tech Stack

- **Language :** Java 21
- **Build Tool :** Apache Maven
- **Framework :** Spring boot 3.5.7
    - Spring Data JPA
    - Spring basic security
    - Spring Web
    - Eureka Client
    - Spring cloud loadbalancer
- **Databases :**
    - MYSQL 8.0 (Docker compose)
    - H2 (development)
- **Security :** 
    - Spring Security
    - OAuth2 Resource Server
- **Testing :** 
    - Spring Boot Testing
    - Mockito
    - JUnit 5

---

## 🏁 Getting started
### Prerequisites

- Java 21
- Maven
- Docker 
- Postman
- Keycloak
---

### 🔌 Ports
#### Connections
- **Eureka :** `8761`
- **Gateway :** `4545`
- **MySQL :** `3307`
- **User :** `8686`
- **Keycloak :** `8080`

#### Media connections
- **Creator :** `8787`
- **Genre :** `8585`
- **Thumb :** `8484`
- **Utility :** `8888`

#### Media services
- **Pod :** `8282`
- **Video :** `8383`
- **Music :** `8181`

---

## 🔒 Authentication & Roles

This service uses **OAuth2** and **Keycloak** for authentication and authorization.

- **User Roles:**
        - **edufy_realm_admin** Can reach all admin endpoints in all microservices
        - **pod_admin:** Can show podcasts with id, create new podcasts
        - **pod_user:** Can view podcasts, get by title or genre, play podcast episode

> _Note: These are not "real" users/admin. They are placeholders for production and used under development._
>
| Role                |     Username      | Password |
|---------------------|:-----------------:|:--------:|
| pod_admin           |     pod_admin     |  admin   |
| edufy_realm_admin   | edufy_realm_admin |  admin   |
| pod_user            |     pod_user      |   pod    |
| microservice_access |                   |    |

> Note: Unauthenticated requests will receive a `401 Unauthorized` response.

> Note: `microservice_access` is a role that clients uses between each other to authorize access

---

## 📚 API Endpoints

### Admin - Roles `pod_admin` & `edufy_realm_admin`:
* **GET** `/pod/episode-id/{id}` - Gets one Podcast episode by id
* **GET** `/pod/season-id/{id}` - Gets one Podcast season by id
* **POST** `/pod/new-episode` - Create new Podcast episode, uses body
* **POST** `/pod/new-season` - Create new Podcast season, uses body

### Client - Role `microservice_access`:
* **GET** `/pod/user-history/{userId}` - Gets one Users history on podcast episodes. Which episodes they listened to

### Common - Role - just authenticated:
* **GET** `/pod/episode-all` - Lists all Podcast episodes. If admin, inactive episodes and id is shown too
* **GET**` /pod/season-all` - Lists all Podcast seasons. If admin, inactive seasons and id is shown too
* **GET** `/pod/pod-creator/{creatorId}` - Gets all podcasts, both episodes and seasons made by given creator. Seasons are condensed
* **GET** `/pod/season-creator/{creatorId}` - Gets all podcasts, both episodes and seasons made by given creator. Will show season and its episodes

### User - Role `pod_user`:
* **GET** `/pod/episode-title` - Gets all podcast episodes where the title contains the word given in param
* **GET**` /pod/season-title` - Gets all podcast seasons where the title contains the word given in param
* **GET** `/pod/episodes-genre/{genreId}` - Gets all podcast episodes based on the genre given
* **GET** `/pod/play/{episodeId}` - "plays" the given podcast, gives back the podcasts url
> User will not have to write the id if front end is added

---

## 🐳 Docker
- Use `docker-compose.yml` to build and run the project, is in [Edufy-infra](https://github.com/EudfyProjects/Edufy-infra)
- Docker network: `edufy-network`

---

## 🛢️ MySQL Database

| Name               | Username | Password |   Database    |
|--------------------|:--------:|:--------:|:-------------:|
| edufy_mysql        |   assa   |   assa   | main database |
| edufy_pod_db       |   assa   |   assa   |      pod      |

- **Version :** 8.0 
- **SQL file :** 
    - There is a sql file that `docker-compose.yml` uses to create the databases and grant access to `assa`. Can be found in [Edufy-infra](https://github.com/EudfyProjects/Edufy-infra)
    - Pod has 2 sql file, `data.sql` for development and `import.sql` for production. Both are filled with placeholder data to test with in development and production
- **Default Port :** `3306`
    - In this example port is `3307:3306`. If MySQL workbench server is not installed the port can be changed to `3306:3306`
- **Configuration :** To connect to the database, write in `application-prod.properties`
- **Connection Example :**
  ```
    spring.datasource.url=jdbc:mysql://edufy-mysql:3306/edufy_pod_db
    spring.datasource.username=assa
    spring.datasource.password=assa
    spring.jpa.hibernate.ddl-auto=update
  ```

> _README made by [SaraSnail](https://github.com/SaraSnail)_



