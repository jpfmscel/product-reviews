[![CircleCI](https://circleci.com/gh/jpfmscel/product-reviews.svg?style=svg)](https://circleci.com/gh/jpfmscel/product-reviews)

# Product-Reviews
SpringBoot application for products and reviews

## Architecture
- Product Service : microservice responsible for retrieving product data
- Review Service : microservice responsible for retrieving review data
  - MongoDB as database
- Docker Compose orchestrates the containers
- CircleCI as Continuous Integration pipeline  
- Swagger documentation available at **/swagger-ui.html**
- JUnit and Embedded MongoDB for unit and integration testing

## Startup
Navigate to the folder containing the docker-compose.yml file and run the following commands:  

```$ docker-compose build && docker-compose up```

## Docs
- Swagger documentation available for both services
  - Product service : http://localhost:8082/swagger-ui.html
  - Review service  : http://localhost:8083/swagger-ui.html


* [ ] Develop this application with a microservice approach, all services should run independently.
* [ ] Write API tests for your endpoints.
* [ ] Provide API documentation using swagger or similar.
* [ ] Every person having Java and some standard tools should be able to check out the code, build
and run the app locally (both Gradle and Maven accepted).
* [ ] Ensure the design is taking into consideration the SLAs and response times.
* [ ] Please, use English as a documentation language.
* [ ] When you are done, check your solution into any public GIT repo (e.g. GitHub or Bitbucket) and
send us the link.

* [ ] BONUS 1: Dockerize the two component services and create config files for deploying them.
* [ ] BONUS 2: Create a CI/CD pipeline proposal for the app.
* [ ] BONUS 3: Create a test integration suite for both services.

## Possible Future Enhacements
- Enhanced caching (Redis, Caffeine, Inifinspan...);
- Enhanced security using API gateway for microservices communication using JWT and API Tokens;
- Enhanced security using strict networking in Docker setup;
- Enhanced Service Discovery through technologies such as Eureka;
- Optimize Docker setup, improving cache of maven dependencies and reusable containers;
- API monitoring through visual interface technologies such as Prometheus;
- Self management through technologies such as Kubernetes;-
- Enhance i18n by adding and improving feedback messages, in order to provide better UX (User eXperience)
