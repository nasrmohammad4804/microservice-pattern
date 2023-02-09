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
after that axon server start up and dashboard is available you need to start up order service - user-service - productservice and so on .

the next step create product on
<b>http://localhost:8080/swagger-ui/index.html#/product-command-controller/saveProduct</b> url

and use product id that gives on previous step for place order on <b>http://localhost:8081/swagger-ui/index.html#/order-command-controller/placeOrder</b>
url and see how distribute transaction work among of all micorservices
