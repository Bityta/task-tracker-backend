package ru.app.restapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiServiceApplication.class, args);
    }

    //todo
    //документация + тесты
    //@handleBadCredentialsExceptions убрать русские буквы
    //http статут 409 vs 400(bad request)
    //redirect в случиаи ошиибок и тд
    //сообрание микросерфиса
    //пересмотр дто которые выводяться (меняьь и в дто и свагере)
    //method void -> вернуть тело json мб
    //вывод ошибки token
    //сообщение ошибки в виде:
//    {
//        "timestamp": "2024-02-17T18:07:15.166+00:00",
//            "status": 403,
//            "error": "Forbidden",
//            "path": "/user"
//    }

}
