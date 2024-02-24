package ru.app.restapiservice.rabbitMQ.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.app.restapiservice.api.model.dto.email.EmailDto;

@FeignClient("task-tracker-email-sender")
public interface RabbitMQRepository {

    @PostMapping("/message/greetings")
    ResponseEntity<String> sendGreetingsMessage(@RequestBody EmailDto emailDto);


}
