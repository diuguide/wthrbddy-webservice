# Spring Boot REST API

This project is a simple Spring Boot application that serves as a RESTful API for receiving temperature and humidity readings and displaying the latest readings on a jQuery webpage.

## Project Structure

```
springboot-rest-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── springbootrestapi
│   │   │               ├── SpringbootRestApiApplication.java
│   │   │               └── controller
│   │   │                   └── ApiController.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── templates
│   │           └── index.html
├── pom.xml
└── README.md
```

## Endpoints

1. **Receive Temperature and Humidity**
   - **URL:** `/receive/{temp}:{humidity}`
   - **Method:** GET
   - **Description:** Receives temperature and humidity readings.

2. **Display Latest Reading**
   - **URL:** `/`
   - **Method:** GET
   - **Description:** Serves a jQuery webpage that displays the latest temperature and humidity readings.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd springboot-rest-api
   ```

2. **Build the Project**
   Make sure you have Maven installed. Run the following command to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   You can run the application using the following command:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**
   Open your web browser and navigate to `http://localhost:8080` to view the jQuery webpage displaying the latest readings.

## Dockerization

To dockerize the application, create a Dockerfile in the root of the project and follow the instructions to build and run the Docker container.

## Deployment

This application can be deployed to Google Cloud Run. Ensure you have the Google Cloud SDK installed and follow the necessary steps to deploy your containerized application.

## License

This project is licensed under the MIT License.