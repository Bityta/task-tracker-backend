package ru.app.restapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiServiceApplication.class, args);
    }
}
