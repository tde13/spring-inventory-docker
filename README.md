# Inventory Service – spring-inventory-docker

## Overview

The Inventory Service is responsible for tracking the availability of products in the supply chain management system. It manages the stock levels of each product and ensures that inventory data remains consistent and accurate during operations such as stock updates and order placement.

This service relies on the existence of valid products (retrieved from the Product Service) and interacts with the Order Service to validate and update inventory during the order lifecycle. The service is containerized with Docker and uses a dedicated MySQL database for persistence.

---

## Features

- Maintains available stock for products.
- CRUD operations for managing inventory records.
- Ensures each product has at most one inventory record (uniqueness enforced).
- Rejects inventory entries for non-existent products.
- Provides endpoints to retrieve and update inventory quantities.
- Supports inter-service validation during order placement.

---

## Architecture & Design

The Inventory Service follows the **database-per-service** pattern and uses a dedicated MySQL database (`inventorydb`). It is connected to both a private Docker network for internal DB communication and a shared Docker network (`spring-shared-network`) for REST communication with other services.

This design ensures data isolation while enabling service interoperability.

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Docker

---

## Environment Variables

Set the following when running the container:

- `MYSQL_HOST` – Hostname of the MySQL container (e.g., `mysqldb1`)
- `MYSQL_PORT` – MySQL port (default: `3306`)
- `MYSQL_USER` – MySQL username (e.g., `root`)
- `MYSQL_PASSWORD` – MySQL password (e.g., `123456`)

---

## Docker Usage

### Build the Docker Image

```bash
docker build -t spring-inventory-docker .
