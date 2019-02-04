[![CircleCI](https://circleci.com/gh/jpfmscel/product-reviews.svg?style=svg)](https://circleci.com/gh/jpfmscel/product-reviews)

# Product-Reviews
SpringBoot application for products and reviews

## Architecture
- ```Java 11.0.2```
- ```Maven (wrapper)```
- ```Product Service``` : microservice responsible for retrieving product and review data
- ```Review Service``` : microservice responsible for managing review data
- ```MongoDB``` as database
- ```Caffeine``` for caching
- ```Docker Compose``` builds and starts the docker images
- ```CircleCI``` as Continuous Integration pipeline  
- ```Swagger``` documentation available at **/swagger-ui.html**
- ```JUnit``` for unit testing
- ```SpringBoot Actuator``` for service health monitoring
- ```i18n``` ready ([review-service messages folder](https://github.com/jpfmscel/product-reviews/tree/master/review-service/src/main/resources/messages) and [product-service messages folder](https://github.com/jpfmscel/product-reviews/tree/master/product-service/src/main/resources/messages))

## Requirements
 - Docker Compose (MongoDB runs in a container)
 - Java 11 (to run locally in IDE)
 - Unix based OS (Mac OS, Ubuntu, etc)

## Setup
 The applications are configured, thus there is no need for further configuration.  
 At startup, database seed data is inserted into DB if no documents are present in ```Review``` collection.  
   
 **Cache is currently using a TTL of 10 seconds.**

## Startup
Navigate to the folder containing the docker-compose.yml file and run the following commands:  

```$ docker-compose build && docker-compose up```

## API Testing
Postman API requests/tests are available [in this folder](https://github.com/jpfmscel/product-reviews/tree/master/API%20Postman%20Tests)

## Swagger Docs
Swagger documentation available for both services (after docker startup)
 - Product service : http://localhost:8082/swagger-ui.html
 - Review service  : http://localhost:8083/swagger-ui.html


## Possible Future Improvements / Features
- Enhanced cache control;
- Enhanced security using API gateway for microservices communication using JWT and API Tokens;
- Enhanced security using strict networking in Docker setup;
- Enhanced Service Discovery through technologies such as Eureka;
- Optimize Docker setup, improving cache of maven dependencies and reusable containers;
- API monitoring through visual interface technologies such as Prometheus;
- Self management through technologies such as Kubernetes;
- Enhanced i18n by adding and improving feedback messages, in order to provide better UX (User eXperience)
- Improve integration and unit tests with broader coverage
