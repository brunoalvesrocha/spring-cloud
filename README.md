# Spring Cloud Stack

This is a small example of use spring cloud projects.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What is needed? Java 8 and maven.
Its necessary also mysql and a database with name dbservice ou equivalent. For the configuration see application.yml into db-service project.

### How

The first step is: into a favorite IDE import all the projects and execute after.

For the hystrix-turbine is needed to install an image docker with rabbit-mq the command is:

```
docker run -d --hostname local-rabbit --name local-rmq -p 15672:15672 -p 5672:5672 rabbitmq:3.6.9-management
```
after is necessary that starts the new image for use.


### The URI's for test

*eureka dashboard
	http://localhost:8302/
	user: eureka
	pass: eureka

*postman
	GET: http://localhost:8079/api/stock/rest/stock/Sam
	POST: http://localhost:8079/api/stock/rest/stock/add
		body:
		```
		{
			"userName": "Any name",
			"quotes": ["GOOG", "YAHOO"]
		}

		```

*hystrix-dashboard:
	http://localhost:8085/hystrix
	use : http://localhost:8087 into hystrix dashboard for monitoring the streams and the circuit breakers.
			
## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
