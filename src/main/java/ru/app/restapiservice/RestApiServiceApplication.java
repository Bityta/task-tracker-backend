package ru.app.restapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main entry point for the REST API service application.
 */
@SpringBootApplication
@EnableFeignClients
public class RestApiServiceApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RestApiServiceApplication.class, args);
    }
}
