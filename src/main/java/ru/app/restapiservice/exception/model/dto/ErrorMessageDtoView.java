package ru.app.restapiservice.exception.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessageDtoView {

    private HttpStatus status;
    private String fieldName;
    private String error;
    private String path;


    public Map<String, String> getError() {

        Map<String, String> errors = new HashMap<>();
        Date date = new Date(System.currentTimeMillis());

        errors.put("timestamp", String.valueOf(date.toString()));
        errors.put("status", String.valueOf(this.status));
        errors.put("error", this.error);
        errors.put("path", this.path);

        return errors;
    }
}
