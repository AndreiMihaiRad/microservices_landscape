# Centralized configuration

## Topics covered
- Introduction to Spring Cloud Configuration Server
- Setting up a config server
- Configuring clients of a config server
- Structuring the configuration repository
- Trying out the Spring Cloud Configuration Server

![Diagram](diagrams/chapter12_diagram.png)

## Try out config server api
```bash
curl https://dev-usr:dev-pwd@localhost:8443/config/product/docker -ks | jq
```