package ru.app.restapiservice.rabbitMQ.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.app.restapiservice.rabbitMQ.model.dto.EmailGreetingsDto;
import ru.app.restapiservice.rabbitMQ.repository.RabbitMQRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class RabbitMQServiceTest {

    @InjectMocks
    RabbitMQService rabbitMQService;
    @Mock
    private RabbitMQRepository rabbitMQRepository;

    @Test
    public void testSendGreetingsMessage() {
        EmailGreetingsDto emailDto = EmailGreetingsDto.builder()
                .email("test@gmail.com")
                .build();

        when(rabbitMQRepository.sendGreetingsMessage(any(EmailGreetingsDto.class))).thenReturn(null);
        this.rabbitMQService.sendGreetingsMessage(emailDto);
        Mockito.verify(this.rabbitMQRepository, Mockito.times(1)).sendGreetingsMessage(emailDto);
    }
}
