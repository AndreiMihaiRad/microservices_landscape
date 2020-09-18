# Securing Access to APIs

## Topics covered
- An introduction to OAuth 2.0 and OpenID Connect standards
- A general discussion on how to secure the system landscape
- Adding an authorization server to our system landscape
- Protecting external communication with HTTPS
- Securing access to the discovery service, Netflix Eureka
- Authenticating and authorizing API access using OAuth 2.0 and OpenID Connect
- Testing with the local authorization server
- Test with an OpenID Connect provider, Autho.

![Diagram](diagrams/chapter11_diagram.png)

## Credentials
- username: u
- password: p

## Acquiring access tokens using the password grant flow

Get an access token for the writer client, that is, with both the product:read and product:write scopes, issue the following command:
```bash
curl -k https://writer:secret@localhost:8443/oauth/token -d grant_type=password -d username=magnus -d password=password -s | jq
```
For Reader
```bash
curl -k https://reader:secret@localhost:8443/oauth/token -d grant_type=password -d username=magnus -d password=password -s | jq
```

## Acquiring access tokens using the implicit grant flow

Open link in browser
```
https://localhost:8443/oauth/authorize?response_type=token&client_id=reader&redirect_uri=http://my.redirect.uri&scope=product:read&state=48632
```

## Acquiring access tokens using the code grant flow

Open link in browser
```
https://localhost:8443/oauth/authorize?response_type=code&client_id=reader&redirect_uri=http://my.redirect.uri&scope=product:read&state=48632
```
- Extract the Authorization code from the code parameter and define an environment variable CODE, with its value:
```
CDOE=IiImJo
```
- Next, pretend you are the backend server that exchanges the authorization code with an access token using following curl command:
```bash
curl -k https://reader:secret@localhost:8443/oauth/token \
    -d grant_type=authorization_code \
    -d client_id=reader \
    -d redirect_uri=http://my.redirect.uri \
    -d code=IiImJo \
    -s | jq
```

## Calling protected APIs using access token

```bash 
curl -k https://localhost:8443/product-composite/2 -k \
    -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJtYWdudXMiLCJleHAiOjIyMDAzNzI0NzAsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJDaEVndWJMSjduQWtmTWlzazhGTmhwUG1tK3c9IiwiY2xpZW50X2lkIjoicmVhZGVyIiwic2NvcGUiOlsicHJvZHVjdDpyZWFkIl19.iDJMZ3V3-2CxWzJFY8iSzywhS7ocJ88wbknemxvBbUyrZ8f7vtwNbZrSK9arRDSbMOdosgKl5eCPH5OoLmFUhqXuizVwINN1xvywipW9tlxQSyeIzVZR-ufm9mdEalEA6SsDI5Z0ikReCRdVRSSam_fr5HsGD8wml1YpSt4efa-utwcZh1GJyO1cdSJaJK4zsvuWLzld9R9x3eySgGN2pxILH0RQnnDYzwhHzBFH-QxIm1SLO9jf5vwTOITxHvkrjyaeCBdWaOtQ0JSL_R4c_UzXI3YSu2zUR3vY6L7870O2k2Pb1ePWABxMB2hdzEIxUqLAvXJBAY_XrbZuX54p8Q" -i
```



