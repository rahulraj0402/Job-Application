# Job Match Pro ( A job application ) 

This is a project aimed at managing job applications utilizing microservices architecture. The project is built using Java, Spring Boot, Spring Data JPA, and Docker for PostgreSQL database. It also utilizes Eureka for service registry. Additionally, future integration with RabbitMQ and Kubernetes is planned.

## Microservices

### Jobs Microservice
This microservice handles job-related operations such as job posting, updating, and deletion.

### Review Microservice
The Review microservice manages the review process of job applications, allowing for the addition, retrieval, and updating of reviews.

### Companies Microservice
The Companies microservice deals with company-related functionalities like adding new companies, retrieving company details, and updating company information.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Docker (for PostgreSQL database)
- Eureka (Service Registry)
- openFeign( for the inter service communication  [ removed the use of rest template ] ) 
- RabbitMQ for Asynchronous Communication between reviews and companies 
- Kubernetes (Planned for future integration)

## Setup Instructions
1. Clone the repository.
2. Navigate to each microservice directory (jobs, review, companies) and build using Maven .
3. Run Docker to create and start PostgreSQL database containers.
4. Start the Eureka server.
5. Run each microservice.
6. Access the microservices through the Eureka service registry.

## Future Enhancements
- Deployment of microservices on Kubernetes for scalability and resilience.

Feel free to contribute to the project by submitting pull requests or reporting issues.



