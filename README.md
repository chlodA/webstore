# Webstore 

## Technologies
- Java
- Spring Boot MVC
- Hibernate
- MYSQL
- Maven
- Thymeleaf

## Endpoints


| Methods       | Urls                     | Actions                                         |
| ------------- |--------------------------| ------------------------------------------------|
| POST          | /products/create               | create new Product                              |
| GET           | /products                | retrieve all Products                           |
| GET           | /products/:id            | retrieve a Product by :id                       |
| GET           | /products/category/:category | retrieve a Product by :category |
| GET           | /products/category/:name | retrieve a Product by :name |
| GET           | /products/filter/:params | retrieve a Product by :params Sample URL: http://localhost:8080//products/filter/params;brands=Google;category=Tablet|
| PUT           | /products/update/:id            | update a Product by :id                         |
| DELETE        | /products/delete/:id            | delete a Product by :id                         |
| DELETE        | /products                | delete all Products                             |
| GET           | /products?title=[keyword]|find all Products which title contains keyword   |
| GET           | /products?id=[productId]|find all Products which the Product Id   |

###### Project is not finisched yet.


