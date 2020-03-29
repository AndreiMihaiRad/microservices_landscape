# A Micro-Services Landscape project

## Build
### Build All Microservices
```bash
./gradlew build
```

### Build specific microservice
```bash
./gradlew :microservices:product-service:build
```

## Java test resource allocation
- docker limit cpu
```bash
echo 'Runtime.getRuntime().availableProcessors()' | docker run --rm -i openjdk:12.0.2 jshell -q
echo 'Runtime.getRuntime().availableProcessors()' | docker run --rm -i --cpus 3 openjdk:12.0.2 jshell -q
echo 'Runtime.getRuntime().availableProcessors()' | docker run --rm -i --cpu-shares 2048 openjdk:12.0.2 jshell -q
```
- docker limit memory
```bash
docker run -it --rm openjdk:12.0.2 java -XX:+PrintFlagsFinal -version | grep MaxHeapSize
docker run -it --rm -m=1024M openjdk:12.0.2 java -XX:+PrintFlagsFinal -version | grep MaxHeapSize
docker run -it --rm -m=1024M openjdk:12.0.2 java -Xmx800m -XX:+PrintFlagsFinal -version | grep MaxHeapSize
```
- test max heap size
```bash
echo 'new byte[100_000_000]' | docker run -i --rm -m=1024M openjdk:12.0.2 jshell -q
```

- heap larger that allocated size
```bash
echo 'new byte[500_000_000]' | docker run -i --rm -m=1024M openjdk:12.0.2 jshell -q
```

## Docker 
- Build Docker image
```bash
./gradlew :microservices:product-service:build
cd microservices/product-service
docker build -t product-service .
```
- Start docker image
```bash
docker run --rm -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=docker" product-service
```
### Docker-compose
- build docker images
```bash
./gradlew build
docker-compose build
```
- start all services
```bash
docker-compose up -d
```
- stop containers
```bash
docker-compose down
```

- test all containers using bash and compose
```bash
./test-em-all.bash start stop
```

### Useful docker commands
- check containers status
```bash
docker ps -a
```
- check compose service logs
```bash
docker-compose logs product
```

### Combines commands
- clean build and test
```bash
./gradlew clean build && docker-compose build && ./test-em-all.bash start stop
```



## References
 - https://github.com/PacktPublishing/Hands-On-Microservices-with-Spring-Boot-and-Spring-Cloud.git
