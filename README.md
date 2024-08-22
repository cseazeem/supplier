# Makersharks Search API

## Overview

This project provides a proof of concept for a search API that allows buyers to search for manufacturers based on customized requirements. The API is built using Spring Boot, PostgreSQL, JDBI, Liquibase, and JWT for authentication.

## Prerequisites

- JDK 17
- PostgreSQL
- Maven or Gradle

## Setup

1. **Create PostgreSQL Database**

   ```bash
   psql -U postgres
In the PostgreSQL prompt:


CREATE DATABASE supplier_db;
Configure Liquibase

Ensure Liquibase is configured in your application.yml or application.properties file to connect to your PostgreSQL database.

Run Liquibase Migrations


./mvnw liquibase:update
Populate the Database

Run the following SQL script to insert sample data:


DO $$ 
BEGIN 
    FOR i IN 1..1000 LOOP
        INSERT INTO supplier (company_name, website, location, nature_of_business, manufacturing_processes)
        VALUES (
            'Company_' || i, 
            'https://company' || i || '.com', 
            CASE 
                WHEN i % 4 = 0 THEN 'India'
                WHEN i % 4 = 1 THEN 'China'
                WHEN i % 4 = 2 THEN 'Dubai'
                ELSE 'America'
            END,
            CASE 
                WHEN i % 3 = 0 THEN 'small_scale'::nature_of_business
                WHEN i % 3 = 1 THEN 'medium_scale'::nature_of_business
                ELSE 'large_scale'::nature_of_business
            END,
            CASE 
                WHEN i % 4 = 0 THEN 'moulding'::manufacturing_processes
                WHEN i % 4 = 1 THEN '3d_printing'::manufacturing_processes
                WHEN i % 4 = 2 THEN 'casting'::manufacturing_processes
                ELSE 'coating'::manufacturing_processes
            END
        );
    END LOOP;
END $$;
Running the Application
Build the Project


./mvnw clean install
Run the Application


./mvnw spring-boot:run
The application will start at http://localhost:8080.

Authentication
Sign Up
To create a new user, send a POST request to the /auth/signup endpoint:


curl --location 'http://localhost:8080/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "cseazeem@gmail.com",
    "password": "azeem",
    "fullName": "Mohd Azeem"
}'
Login
To log in and receive a JWT token, send a POST request to the /auth/login endpoint:


curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "cseazeem@gmail.com",
    "password": "azeem"
}'
The response will include a JWT token that you can use to authenticate subsequent requests.

Access the Search API
To search for suppliers, use the /api/supplier/query endpoint. Include the JWT token in the Authorization header as a Bearer token.

Example cURL request:


curl --location 'http://localhost:8080/api/supplier/query' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <your_jwt_token>' \
--data-raw '{
    "location": "India",
    "natureOfBusiness": "small_scale",
    "manufacturingProcess": "3d_printing",
    "limit": 5,
    "offset": 5
}'
Replace <your_jwt_token> with the token received from the login endpoint.
