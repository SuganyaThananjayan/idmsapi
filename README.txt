# IDMS API Integration with Spring Boot, Hibernate, and MySQL

This project demonstrates how to integrate with the IDMS API to fetch account data, store it in a MySQL database, and expose a secured REST API endpoint to retrieve this data. It uses **Spring Boot**, **Hibernate**, and **MySQL** for persistence. The integration includes **JWT authentication** for securing the API endpoint.

## Prerequisites

Before you start, make sure you have the following:

- **Java 8+** installed
- **MySQL** installed and running
- **Postman** for API testing

### Environment Variables (in `application.properties`)

1. **IDMS API Credentials:**
    - **Base URL**: `https://idms.dealersocket.com`
    - **Username**: `testerAPI@drivesoft.tech`
    - **Password**: `HeoVIaCST3st@@Main`
    - **LayoutID**: `2006084`
    - **AccountStatus**: `a`
    - **InstitutionID**: `107007`
    - **PageNumber**: `1`

2. **MySQL Configuration:**
    - **Database URL**: `jdbc:mysql://localhost:3306/idms_db`
    - **Database User**: `root`
    - **Database Password**: `rootpassword`

### Dependencies

1. **Spring Boot** (Web, Data JPA, Security)
2. **Hibernate** for ORM
3. **MySQL** Connector
4. **JWT** for token-based authentication

### Setup Instructions

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/yourusername/idms-integration.git
    cd idms-integration
    ```

2. **Install Dependencies:**

    - Make sure that you have **Maven** or **Gradle** installed.
    - Run the following to install dependencies:

    ```bash
    mvn install
    ```

3. **Set Up Database:**

    - Create a new database in MySQL:

    ```sql
    CREATE DATABASE idms_db;
    ```

    - Run the **SQL script** below to create the necessary tables.

4. **Configure `application.properties`:**

    - Open `src/main/resources/application.properties` and set your database and API configuration according to your environment.

    Example:

    ```properties
    # API Configuration
    api.base-url=https://idms.dealersocket.com
    api.username=testerAPI@drivesoft.tech
    api.password=HeoVIaCST3st@@Main
    api.layout-id=2006084
    api.account-status=a
    api.institution-id=107007
    api.page-number=1

    # MySQL Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/idms_db
    spring.datasource.username=root
    spring.datasource.password=rootpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    ```

5. **Run the Application:**

    Run the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

6. **Test the Application:**

    - Use **Postman** to send requests to the API (details below in the Postman collection).

### API Endpoints

1. **Authentication Endpoint (`POST /auth/login`)**:
    - Use the username and password (admin/DriveSoft@@!) to obtain a JWT token.
    - Response: `{ "token": "<JWT_TOKEN>" }`

2. **Vehicle Data Endpoint (`GET /vehicle-data`)**:
    - This endpoint returns the list of vehicle data stored in MySQL.
    - Header: `Authorization: Bearer <JWT_TOKEN>`

---

### **SQL Script to Create MySQL Tables**

```sql
CREATE DATABASE idms_db;

USE idms_db;

CREATE TABLE account (
    acctId BIGINT PRIMARY KEY,
    contractSalesPrice DOUBLE,
    acctType VARCHAR(255),
    salesGroupPerson1ID BIGINT,
    contractDate DATE,
    collateralStockNumber VARCHAR(255),
    collateralYearModel INT,
    collateralMake VARCHAR(255),
    collateralModel VARCHAR(255),
    borrower1FirstName VARCHAR(255),
    borrower1LastName VARCHAR(255)
);
