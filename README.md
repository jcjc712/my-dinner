## My dinner app
This repo has the endpoints to handle customers, dishes and orders resources.

### Run

#### Create, build and run docker containers
It creates a mysql container and my-dinner app, and run db migrations.
```sh
docker-compose up
```

### Consume service
Make requests to `http://localhost:8080` besides all the end-point 
where documented in a POSTMAN collection

### Testing
In my-dinner container run the test command
```sh
docker exec -it my-dinner_backend_1 ./mvnw test
```
