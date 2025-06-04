<h1 align="center"> Spring Microservices - Reservation System </h1>
<h2 align="left">Languages and Tools:</h2>

<p align="left"> <a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="50" height="50"/> </a> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="50" height="50"/> </a> <a href="https://kafka.apache.org/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/apache_kafka/apache_kafka-icon.svg" alt="kafka" width="50" height="50"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="50" height="50"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="50" height="50"/> </a> </p>
<br/>
<p>
<h2>Key Microservice Patterns Used</h3>
<h4>Saga Pattern:</h4> Primarily used in the refund process to ensure that a series of local transactions are all executed or compensated. Each step is an individual local transaction and has a compensating transaction.

Example: If the refund is not processed, the reservation is not canceled, and the user is notified.

<h4>API Gateway Pattern:</h4> All client requests go through an API Gateway (Zuul/Spring Cloud Gateway), which routes requests to appropriate services.

Example: A user makes a reservation. The API gateway routes this request to the Reservation Service, which then talks to the Payment Service and Hotel Management Service.

<h4>Proxy Pattern:</h4> When you have to interact with an external service or library, wrap it within a Proxy Service. The Proxy pattern will help you control access, logging, and security features for accessing the third-party or costly resources.

Example: For sending SMS and Email notifications, instead of calling third-party services directly, they could be routed through a Notification Proxy that batches requests or caches repeated requests to the same number/email to reduce costs.

</p>
<h2>Microservices</h2>

* <h3>API Gateway:</h3> API Gateway Pattern to route to core services
* <h3>Eureka Registry:</h3>Used for service discovery/registration
* <h3>Customer Service:</h3>Handles customer profiles and preferences.
* <h3>Reservation Service:</h3>Manages reservation logic.
* <h3>Payment Service:</h3>Handles payment transactions.
* <h3>Hotel Management Service: </h3>Manages room inventories.
* <h3>Notification  Service: </h3>Sends out notifications.
<p>
<h2>Pre-requisites to Build & Run Services</h2>
<ul>
  <li><h4>Java 17</h4></li>
  <li><h4>Maven</h4></li>
  <li><h4>Docker Engine</h4></li>
  <li><h4>Postman</h4>  </li>
</ul>
</p>
<h2>How to run services</h2>
<ul>
<li>Run build-projects.bat in root to compile and build jars</li>
<li>Run docker-compose up in root folder to build docker images and start kafka/zookeeper followed by all services</li>
<li>Access <a href="http://localhost:8761">http://localhost:8761</a> to view services</li>
<li>Liquibase scripts would pre-load dataset in h2 for testing</li>
<li>Run attached postman tests to create hotel reservation and cancel reservation</li>
<li>Access <a target="_blank" href="https://www.wpoven.com/tools/free-smtp-server-for-testing">Email Server</a> and check email box noreply@amhotels.com to look for hotel booking/cancellation email notifications</li>
</ul>
<h2> Postman Script </h2>
Postman API test scripts can be found below.
[link](./postman-tests/Capstone Project.postman_collection)

