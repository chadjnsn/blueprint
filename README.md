<img src="blueprint.png" />

[![Build Status](https://travis-ci.org/chadjnsn/blueprint.svg?branch=master)](https://travis-ci.org/chadjnsn/blueprint)

This is a simple project that demonstrates how Spring Cloud components and Netflix components interact and can be configured.

##Getting Started##

After cloning this repo locally, you can start the server as a standard spring boot application.
```bash
./gradlew bootRun
```

Once the application is running, you can access it via any web browser or HTTP client. By default it runs on port 9000, however this can be changed by editing the application.yml file.

##Components##
There are several components demonstrated in this project which are detailed below.

###Security###
There are three security endpoints. One that can be accessed by anyone, one that can be accessed by any authenticated user, and one that requires an authenticated user with the 'ADMIN' role assigned.

| Security Level  | Endpoint |
| ------------- | ------------- |
| Anyone  | http://localhost:9000/demo/info  |
| Any Authenticated User  | http://localhost:9000/demo/secure/userinfo  |
| ADMIN Users  | http://localhost:9000/demo/secure/admininfo  |

###Hystrix Circuit Breaker###
There is one endpoint that accesses a service which is wrapped in a hystrix command. The command itself has a 50% chance to fail. If it does fail, then hystrix will return a message from a fallback method. If it fails enough times, then the circuit breaker will trip.

This project also includes a hystrix dashboard so you can monitor the status of the circuit breaker. It is available by default at http://localhost:9000/hystrix and can be pointed at the local hystrix stream by directing the dashboard to http://localhost:9000/hystrix.stream.

Once the dashboard is up and running, you can access a hystrix protected endpoint at http://localhost:9000/demo/unstableinfo

###Distributed Tracing w/ Sleuth###
Sleuth is a library specifically designed for distributed tracing. For more information about distributed tracing as it applies to microservices, check out the [Google Dapper Paper](http://research.google.com/pubs/pub36356.html).

Sleuth provides a simple way to add/log some header values to your REST API which then allow the requests to that API to be traced along with other upstream/downstream requests. For specific info on sleuth, check out the [documentation](http://cloud.spring.io/spring-cloud-sleuth/). In our case, we have a sleuth sampler already configured. We are using a percentage sampler which will trace a percentage of our traffic, so as to avoid filling our logs with tracing info. You can configure the percent of traffic being traced with a property in the application.yml config file as follows.

```yml
spring:
  sleuth:
    sampler:
      percentage: 0.25 # Percentage of requests to sample with sleuth
```
