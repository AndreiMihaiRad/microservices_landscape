# Chapter 06 - Adding Persistence

## Deployment diagram
![Deployment diagram](diagrams/chapter06_deployment_diag.png)

## New Functionalities
- added entities
- added repositories
- added tests
- product new apis
    - create product
    - delete product
    
    **Important: The implementation of delete operation will be idempotent,
    that is, it will return the same result if called several times. This is a valuable characteristic in fault scenarios.
    This implies that the operation should return the status code OK(200) even though the entity no longer exists in the database**

- recommendation new apis
    - create recommendation
    - delete recommendation

## Commands
- execute persistence test in product service
```bash
./gradlew microservices:product-service:test --tests PersistenceTests
```

- start MongoDB CLI tool, mongo, inside the mongodb container
```bash
docker-compose exec mongodb mongo --quiet
# i.e
docker-compose exec mongodb mongo product-db --quiet --eval "db.products.find()"
```

- start MySQL CLI tool, mysql, inside the mysql container and log in to review-db using the user createde at startup
```bash
docker-compose exec mysql mysql -uuser -p review-db
#password cand be find in docker-compose file
#i.e
docker-compose exec mysql mysql -uuser -p review-db -e "select * from reviews"
```

- build and start the system landscape
```bash
./gradlew build && docker-compose build && docker-compose up
```