package ru.app.restapiservice.rabbitMQ.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.rabbitMQ.model.dto.email.EmailDto;
import ru.app.restapiservice.rabbitMQ.repository.RabbitMQRepository;

/**
 * Service class for sending greetings messages via RabbitMQ.
 */
@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final RabbitMQRepository rabbitMQRepository;

    /**
     * Sends a greetings message via RabbitMQ.
     *
     * @param emailDto The EmailDto object containing the email address to send the message to.
     * @throws FeignException If an error occurs while communicating with the RabbitMQ service.
     */
    public void sendGreetingsMessage(EmailDto emailDto) throws FeignException {
        rabbitMQRepository.sendGreetingsMessage(emailDto);
    }
}
