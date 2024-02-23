package ru.app.restapiservice.rabbitMQ.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.rabbitMQ.repository.RabbitMQRepository;

@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final RabbitMQRepository rabbitMQRepository;

    public void sendGreetingsMessage(String email) throws FeignException {
        this.rabbitMQRepository.sendGreetingsMessage(email);
    }
}
