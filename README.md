# Archaeodocs Spring Boot API

**Backend REST API** for archaeological data management project.


## Project Description
Archaeodocs is a full-stack application for recording and managing archaeological sites, artifacts, and documentation. 
This repository contains the Spring Boot backend, which serves as a REST API for the frontend available at:
https://www.brankomikusic.com/Archaeodocs/about.php


## Technologies
- Java 21 + Spring Boot 4.0.1
- Spring Data JPA (Hibernate)
- MySQL (relational database)
- Maven (build tool)
- REST API (JSON format)

## Public API Endpoints
Feel free to test these endpoints (GET requests):
- 🔍 Projects: /api/public/projects &nbsp;&nbsp;https://archaeodocs-spring-api-production.up.railway.app/api/public/projects
- 🏛️ Sites: /api/public/sites &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://archaeodocs-spring-api-production.up.railway.app/api/public/sites
- 🏺 Finds: /api/public/finds &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://archaeodocs-spring-api-production.up.railway.app/api/public/finds
- (Authentication is required for other endpoints.)

## Example Request
Fetch all projects:

`curl -X GET "https://archaeodocs-spring-api-production.up.railway.app/api/public/projects"`

Response (JSON format):
json
`
[
  {
    "id": 1,
    "name": "Site X",
    "comment": "Protective archaeology project",
    "Description": "Some description Lorem Ipsum .."
  }
]
`
## Contact
👤 Branko Mikušić
📧 bmikusic@gmail.com
🔗 https://www.linkedin.com/in/branko-mikusic/
