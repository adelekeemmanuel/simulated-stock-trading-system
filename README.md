#  Stock Trading Platform (Spring Boot Backend)

A **RESTful Stock Trading Platform backend** built with **Spring Boot** that allows users to manage portfolios, trade stocks, and track transactions. The project follows **clean architecture principles**, uses **PostgreSQL** for persistence, and includes **Swagger/OpenAPI documentation** for easy API exploration.

This project is designed as a **real-world backend system** suitable for learning, internships, and portfolio showcasing.

---

##  Features

*  User management (create, update, fetch users)
*  Stock management
*  Portfolio & portfolio-stock tracking
*  Buy & sell stock transactions
*  Transaction history per user
*  PostgreSQL database integration
*  Environment-based secrets management
*  Swagger UI for API documentation
*  Ready for unit & integration testing

---

##  Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA (Hibernate)**
* **PostgreSQL**
* **Spring Security (basic configuration)**
* **Swagger / OpenAPI (springdoc-openapi)**
* **Maven**

---


##  Configuration & Secrets Management

Sensitive database credentials are **not hardcoded**.

### `application.yml`

```yaml
spring:
  config:
    import: "optional:file:src/main/resources/application-secrets.yml"

  datasource:
    url: jdbc:postgresql://localhost:5432/stock
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
```

### `application-secrets.yml` (ignored by Git)

```yaml
spring:
  datasource:
    username: your_db_username
    password: your_db_password
```

 The `application-secrets.yml` file is added to `.gitignore` to prevent leaking credentials.

---

##  Database Setup (PostgreSQL)

1. Install PostgreSQL
2. Create a database:

   ```sql
   CREATE DATABASE stock;
   ```
3. Update your credentials in `application-secrets.yml`
4. Run the application — Hibernate will auto-generate tables

---

##  Running the Application

```bash
mvn spring-boot:run
```

Application runs on:

```
http://localhost:8080
```

---

##  API Documentation (Swagger)

Swagger UI is enabled for easy API testing:

 **Swagger UI**

```
http://localhost:8080/swagger-ui.html
```

 **OpenAPI JSON**

```
http://localhost:8080/v3/api-docs
```

---

##  Security Notes

* CSRF disabled for development
* H2 console support removed (PostgreSQL in use)
* All endpoints currently open (can be secured later with JWT)

---


##  Acknowledgements

This project was built as part of a learning journey in **Java Spring Boot backend development**, focusing on clean code, real-world architecture, and best practices.

If you found this project useful, feel free to ⭐️ the repository
