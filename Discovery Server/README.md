## Discovery Server
Hard-coded URLs are bad for our micro-services.
To solve the issue we need a mechanism to discover micro services when necessary.

So, the need of this project is to work as a server which talks to the client as
well as services.

In this project we used Eureka server.

Eureka Server is an application that holds the information about all client-service applications. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address. Eureka Server is also known as Discovery Server.

Steps of creating a eureka server is - 
* Create a spring boot application with dependency called
Eureka Server.
* Modify application.properties with these two line
```
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```
* Add @EnableEurekaServer annotation above application class.
* Start the application & go to the address.
* You will see default spring eureka server. 