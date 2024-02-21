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

    //todo
    //документация + тесты
    //private final String DEFAULT_ROLE = "ROLE_USER";

    //изменени сфагера под микросерфисы


    //потом:
    //сообрание микросерфиса
    //@handleBadCredentialsExceptions убрать русские буквы
    //пересмотр дто которые выводяться (меняьь и в дто и свагере)
    //path v error (некоторые)
    //logger mb
    //redirect в случиаи ошиибок и тд (для фронт энда)
    //deploy

}
