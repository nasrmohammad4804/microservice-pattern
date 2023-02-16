# microservice-pattern
this repository for learn DDD concept and implement microservice pattern  such as (SAGA - CQRS - Event Sourcing)
-----------------------------------------------------------------------------------------------------------
run axon server on your machine with docker-compose.yaml file (axon framework is introduced for building scalable & event-driven
system and use concept of DDD and CQRS )

for run axonserver use <b>docker-compose up</b> in root package that project cloned
after that it start up on tomcat with port 8024 for http and 8124 for grpc
----------------------------------------------------------------------------------
![axon](https://user-images.githubusercontent.com/76038143/216843348-85062c20-3cc6-4cab-8c58-6c22ff812dab.png)
-------------------------------------------------------------------------
after that axon server start up and dashboard is available you need to start up order service - user-service - product-service and so on .

the next step create product on
<b>http://localhost:8080/swagger-ui/index.html#/product-command-controller/saveProduct</b> url

and use product id that gives on previous step for place order on <b>http://localhost:8081/swagger-ui/index.html#/order-command-controller/placeOrder</b>
after that if every thing is ok orderCreatedEvent and productReservedEvent processed 
but may be complete payment process takes time then we set schedule with DeadlineManager
and if user dont pay order with orderId that received in pervious step with url <b>http://localhost:8083/swagger-ui/index.html#/payment-controller/pay</b>

then scheduler triggered and cancel product reservation and also after that reject Order 
but if less than 1 hour user pay order then continue saga flow with PaymentProcessedEvent and finally orderApproved
