package ru.app.restapiservice.rabbitMQ.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.app.restapiservice.rabbitMQ.model.dto.email.EmailDto;

/**
 * Feign client interface for communicating with a RabbitMQ service to send email messages.
 */
@FeignClient("task-tracker-email-sender")
public interface RabbitMQRepository {

    /**
     * Sends a greetings message via RabbitMQ.
     *
     * @param emailDto The EmailDto object containing the email address to send the message to.
     * @return ResponseEntity containing the response from the RabbitMQ service.
     */
    @PostMapping("/message/greetings")
    ResponseEntity<String> sendGreetingsMessage(@RequestBody EmailDto emailDto);
}
