# Microservices-OpenFeign-OrderManagement-system

Implemented an order management system that has two microservices: Order Service and
Order Item Service with a discovery service. The Order Service is responsible for creating new orders, retrieving
existing order info. Similarly, Order Item Service is responsible for creating order items,
retrieving order items.   

Order Item has below data:
- Product code
- Product name
- Quantity   

Order has below data:
- Customer name
- Order date
- Shipping address
- Order items
- Total ($)   

Tech requirements:
- Used FeignClient for inter-service communication
- Used H2 DB to store order & order item data
- Used global exception handler to handle exceptions like OrderNotFound
- Validated request input
- Exposed APIs as REST APIs
