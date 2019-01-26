#!/bin/sh
cd review-service && ./mvnw clean package & cd product-service && ./mvnw clean package
