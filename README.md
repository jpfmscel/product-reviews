[![CircleCI](https://circleci.com/gh/jpfmscel/product-reviews.svg?style=svg)](https://circleci.com/gh/jpfmscel/product-reviews)

# Product-Reviews
SpringBoot application for products and reviews

## Architecture
- ```Java 11.0.2```
- ```Product Service``` : microservice responsible for retrieving product and review data
- ```Review Service``` : microservice responsible for managing review data
- ```MongoDB``` as database
- ```Docker Compose``` orchestrates the containers
- ```CircleCI``` as Continuous Integration pipeline  
- ```Swagger``` documentation available at **/swagger-ui.html**
- ```JUnit``` for unit testing

## Requirements
 - Docker Compose (MongoDB runs in a container)
 - Java 11 (to run locally in IDE)
 - Unix based OS (Mac OS, Ubuntu, etc)

## Setup
 The applications are configured, thus there is no need for further configuration.  
 At startup, database seed data is inserted into DB if no documents are present in ```Review``` collection

## Startup
Navigate to the folder containing the docker-compose.yml file and run the following commands:  

```$ docker-compose build && docker-compose up```

## Swagger Docs
- Swagger documentation available for both services
  - Product service : http://localhost:8082/swagger-ui.html
  - Review service  : http://localhost:8083/swagger-ui.html


## Possible Future Enhacements
- Enhanced caching (Redis, Caffeine, Inifinspan...);
- Enhanced security using API gateway for microservices communication using JWT and API Tokens;
- Enhanced security using strict networking in Docker setup;
- Enhanced Service Discovery through technologies such as Eureka;
- Optimize Docker setup, improving cache of maven dependencies and reusable containers;
- API monitoring through visual interface technologies such as Prometheus;
- Self management through technologies such as Kubernetes;-
- Enhance i18n by adding and improving feedback messages, in order to provide better UX (User eXperience)
