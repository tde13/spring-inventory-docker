# spring-inventory-docker
spring-inventory-docker is a microservice responsible for managing the inventory or stock levels of products in the system. This service tracks the available quantity of each product in stock and provides real-time data to ensure that the correct amount of inventory is available for orders. It interacts with the product catalog, ensuring that inventory levels are updated when products are sold or restocked.

## It exposes APIs for:

- Checking inventory levels: For any given product, the inventory service returns the available quantity.

- Updating inventory levels: When an order is placed, the inventory service can be updated by reducing the stock based on the quantity ordered.

## Key features:

- Managing the available quantity of each product.

- Updating the inventory when an order is placed.

- Providing APIs to check and update the available quantity for a product.
