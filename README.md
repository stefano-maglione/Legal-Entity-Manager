# Legal Entity Manager

Legal Entity Manager is a RESTful app to manage legal entities and shareholders

## Installation

```bash
clone the project
mvn spring-boot:run
```

## Usage
Create an entity
```curl
curl -v --location --request POST 'http://localhost:8080/entity' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Tesla",
    "incorporationDate" : "2010-10-24",
    "country" : "US",
    "maxShares": 3
}'
```
Retrieve an entity by id
```curl
curl -v --location --request GET 'http://localhost:8080/entity/1' \
--header 'Content-Type: application/json'
```
Update an entity
```curl
curl --location --request PUT 'http://localhost:8080/entity/3' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Tesla",
    "incorporationDate" : "2010-10-24",
    "country" : "US",
    "maxShares": 4
}'
```
Delete an entity
```curl
curl -v --location --request DELETE 'http://localhost:8080/entity/1'
```
Add a shareholder to the entity
```curl
curl -v --location --request PATCH 'http://localhost:8080/entity/1/shareholder' \
--header 'Content-Type: application/json' \
--data-raw '{
  "holderId":2,
  "name": "ShareName",
  "quote" : "1"
}'
```
Retrieve entity shareholders
```curl
curl -v --location --request GET 'http://localhost:8080/entity/1/shareholder'
```
## Example
Create a new legal entity
```curl
curl -v --location --request POST 'http://localhost:8080/entity' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Tesla",
    "incorporationDate" : "2010-10-24",
    "country" : "US",
    "maxShares": 3
}'
```
Create a second legal entity
```curl
curl -v --location --request POST 'http://localhost:8080/entity' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Sony",
    "incorporationDate" : "2005-04-15",
    "country" : "US",
    "maxShares": 5
}'
```
Add the second legal entity as shareholder to the first legal entity
```curl
curl -v --location --request PATCH 'http://localhost:8080/entity/1/shareholder' \
--header 'Content-Type: application/json' \
--data-raw '{
  "holderId":2,
  "name": "ShareName",
  "quote" : "1"
}'
```
Retrieve first entity shareholders
```curl
curl -v --location --request GET 'http://localhost:8080/entity/1/shareholder'
```


## Tests
```bash
mvn test spring-boot:run
```


