# Adding Service Discovery Using Netflix Eureka and Ribbon

## Topics covered
- Introduce to service discovery
    - The problem with DNS-based service discovery
    - Challenges with service discovery
    - Service discovery with Netflix Eureka in Spring Cloud
- Setting up a Netflix Eureka server
- Connecting microservices to a Netflix Eureka server
- Setting up configuration for use in the development process
- Trying out the discovery service

## Build microservices
```bash
./gradlew clean build
```
```bash
docker-compose build
```

## Start microservices
```bash
docker-compose up -d
```

## Scale up microservices
```bash
docker-compose up -d --scale review=3
```

## Check Eureka UI
- [Eureka UI](http://localhost:8761)

## Verify Requests with Review-Service scaled-up to 3
```bash
curl localhost:8080/product-composite/2 -s | jq -r .serviceAddresses.rev
```

- Output should return different ip address after each call
```bash
faa43dcf45a5/172.31.0.11:8080
2113bf13490b/172.31.0.7:8080
1042967288db/172.31.0.10:8080
```

## Scale down
After shutdown of the review instance, there is a short time slot when calls to the API might fail.
This is caused by the time it takes for information regarding the lost instance to propagate to the client, that is,
the product-composite service. During this time frame, the client-side load balancer might choose the instance that no longer exists.
To prevent this form occurring, resilience mechanism such as timeouts and retries ca be used.
For now, let's specify a timeout on our curl command, using the -m 2 switch to specify that will wait no longer than two seconds for a response.

```bash
docker-compose up -d --scale review=2
```

```bash
curl localhost:8080/product-composite/2 -m 2

```