# Carbon Cell - BackendDev Assessment



## Tasks : 

```
Task 1: Implement User Authentication with JWT
Task 2: Create API Endpoints for Data Retrieval from a public API :'https://api.publicapis.org/entries'.
Task 3: Implement Swagger Documentation 
Task 4: Secure API Endpoint for Authenticated Users Only 
```

## Description

This Spring Boot project serves as a robust foundation for building secure RESTful APIs with user authentication using JWT tokens and integration with public APIs. It includes Swagger documentation for easy API exploration and ensures that certain endpoints are accessible only to authenticated users.

##  Prerequisites
Before you begin, ensure you have met the following requirements:

**Java Development Kit (JDK):** Install JDK 17. You can download it from Oracle's website !.

**Database:**  Make sure you have the MYSQL database server installed and configured.

**Development Environment:** Set up your preferred development environment, such as IntelliJ IDEA, Eclipse, or Visual Studio Code, with appropriate plugins for Java and Spring Boot development.

**Version Control:** Use a version control system like Git to manage  project's source code.

## Endpoints:
### Public Endpoints :
* ####  /public/home
``` 
URI:    localhost:8080/public/home
Method: Get
Status Code : 200
Data-Type: JSON

Request Body :
{

}
 Response Body :
   {
       Hii This is non Secure Endpoint
   }
  

```
* #### /auth/register 
```
URI:   localhost:8080/auth/register 
Method: Post
Status Code : 201
Data-Type: JSON

Request Body :
{
    {
    "userName":"user1234",
    "name":"Amit kumar",
    "email":"amit@gmail.com",
    "password":"1234",
    "role":"USER"
    }
}

Response Body :
{
    User registered Successfully
}
```

* #### /auth/login 
```
URI:   localhost:8080/auth/register 
Method: Post
Status Code : 201
Data-Type: JSON

Request Body :
{
   {
    "userName":"user1234",
   "password":1234
    }
}

Response Body :
{
    token **************
}
```
### Secured Endpoints :
These Endpoints can be accessed by Authenticated User Only.

* #### /user/users
From this API User which have Role - **USER** can access this Resorce and get All Registered Users.
```
URI:   localhost:8080/user/users 
Method: Get
Status Code : 200
Header   : Authorization 
Data-Type: JSON

Request Body :
{
   Header: Authorization 
   Type: Bearer Token
}

Response Body :
{
   [
    {
        "userId": 1,
        "userName": "ad1234",
        "name": "Amit kumar",
        "email": "amit@gmail.com",
        "role": "ADMIN"
    },
    {
        "userId": 2,
        "userName": "user1234",
        "name": "Amit kumar",
        "email": "amit@gmail.com",
        "role": "USER"
    }
   ]
}
```
* #### /admin/dashboard
From this API User which have Role - **ADMIN** can access this Resorce and get the Response.
```
URI:   localhost:8080/admin/dashboard 
Method: Get
Status Code : 200
Header   : Authorization 
Data-Type: JSON

Request Body :
{
   Header: Authorization 
   Type: Bearer Token
}

Response Body :
{
   This is Admin DashBoard only Admin can Access This
}
```
* #### /private/secured
From this API Any Authenticated User can access this Resorce and get the Response.
```
URI:   localhost:8080/private/secured
Method: Get
Status Code : 200
Header   : Authorization 
Data-Type: JSON

Request Body :
{
   Header: Authorization 
   Type: Bearer Token
}

Response Body :
{
   This Endpoint accessible Any Authenticated User..
}
```
* #### /auth/logout
From this API Any Authenticated User can logout yourself by the JWT token.To use the Resource again user have to first Login.
```
URI:   localhost:8080/auth/logout
Method: Get
Status Code : 200
Header   : Authorization 
Data-Type: JSON

Request Body :
{
   Header: Authorization 
   Type: Bearer Token
}

Response Body :
{
   Message :You have successfully  logout
}
```
### Secured Endpoints for Data Retrival from Public API
 Data reteriving from this public [API](https://api.publicapis.org/entries)
* #### /filterByCount/1
This Endpoint accessible for Authenticated any type  User
```
URI:   localhost:8080/filterByCount/1
Method: Get
Status Code : 200
Header   : Authorization 
Data-Type: JSON

Request Body :
{
   Header: Authorization 
   Type: Bearer Token
}

Response Body :
{
   {
    "entriesCount": 1,
    "message": "Data Fetch Successfully..",
    "entries": [
        {
            "Description": "Resource to help get pets adopted",
            "Category": "Animals",
            "HTTPS": "true",
            "Auth": "apiKey",
            "API": "AdoptAPet",
            "Cors": "yes",
            "Link": "https://www.adoptapet.com/public/apis/pet_list.html"
        }
    ]
        }
}
```
* #### /filterByCategory
This Endpoint accessible for Authenticated any type  User
```
URI:   localhost:8080/filterByCategory?category=Business
Method: Get
Status Code : 200
Header   : Authorization 
Data-Type: JSON

Request Body :
{
   Header: Authorization 
   Type: Bearer Token
}

Response Body :
{
   {
    "entriesCount": 2,
    "message": "Data Fetch Successfully..",
    "entries": [
        {
            "Description": "API to manage your BI dashboards and data sources on Superset",
            "Category": "Business",
            "HTTPS": "true",
            "Auth": "apiKey",
            "API": "Apache Superset",
            "Cors": "yes",
            "Link": "https://superset.apache.org/docs/api"
        },
        {
            "Description": "Non-profit charity data",
            "Category": "Business",
            "HTTPS": "false",
            "Auth": "apiKey",
            "API": "Charity Search",
            "Cors": "unknown",
            "Link": "http://charityapi.orghunter.com/"
        }
    ]
   }
}
```

### Technologies Used
```
- Programming Language: Java
- Web Framework: SpringBoot
- Database : The project utilizes the MySQL database for data storage.
- Hibernate (for ORM)
- Maven (for dependency management)

```
## Validations :
```
-- User-Name value-null or  Duplicate not accepted.
-- Role value-null not accepted
```

## EndPoints Http Response Code
| Http Response Code | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `201` | `Resource Created` | `Success ` |
| `404` | `not found`    | `whatever you trying to find that not found`  |
| `400` | `Bad Request` | `Input misMatch`  |
| `200`   |`OK`   | `All OK` |
| `401`   |`UnAuthenticated User`   | `Try to Access a Resource without login ` |
| `403`   |`UnAuthorize Access`   | `Try to access a Resource which is not accessible from your Account` |

## In case of Request Failure Http Status and Error Message
| Http Response Code     | Error Message                |
| :-------- | :------------------------- |
| `401`    | `Message: Invalid UserName or password , Try again..` |
| `403`    | `Error message : You does not have permission to access this resource` |
| `409`    | `Message: "User Already exists with user Name : XXXX"` |
| `401`    | `"Error Message : Invalid JWT token , Please check.."` |
| `401`    | `"Error Message : Missing Authorization Header...."` |
| `401`    | `"Error Message :  JWT token Expired..  Please login Again.."` |
| `400`    | `Message: "User password  can't be null ""` |
| `400`    | `Message: "User's user-name  can't be null ""` |
| `400`    | `Message: "User's Role   can't be null ""` |
| `500`    | `Error occurred while processing the request.(This Error can occurs when reterive the data from public API)` |

* All Exceptions and Edge cases are handled effeciently .
* Showing proper Error message and HTTP Status code for Users better understanding.


## Documentations :
* For Testing Apis  you can use **Postman** [Documentation](https://documenter.getpostman.com/view/27811473/2sA35HZ2Rd).
* For better understanding of Endpoints and also you can tests All Endpoints with  **Swagger-UI** [Documentation](http://localhost:8080/swagger-ui/index.html#/)
### Author
 👨‍💼 **Ankit Kumar**
 + Github : [Ankit kumar](https://github.com/ankitk55?tab=repositories)
 + Linkdin : [Ankit Kumar](https://www.linkedin.com/in/ankit-kumar-7300581b3/)
 
### 🤝 Contributing
Contributions, issues and feature requests are Welcome!\
Feel free to check [issues Page](https://github.com/issues) 

### Show Your Support 
 Give a ⭐ if this project help you!
