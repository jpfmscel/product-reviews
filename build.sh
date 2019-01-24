docker-compose up mongo && cd product-service && ./mvnw clean package & cd review-service && ./mvnw clean package & cd auth-service && ./mvnw clean package && docker-compose down mongo
