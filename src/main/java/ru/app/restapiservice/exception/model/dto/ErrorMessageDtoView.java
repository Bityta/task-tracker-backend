package ru.app.restapiservice.exception.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessageDtoView {

    private HttpStatus status;
    private String fieldName;
    private String error;
    private String path;
    private String timestamp;

    public Map<String, String> getError() {
        Map<String, String> errors = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = dateFormat.format(new Date());

        errors.put("timestamp", formattedTimestamp);
        errors.put("status", String.valueOf(this.status));
        errors.put("error", this.error);
        errors.put("path", this.path);

        return errors;
    }
}
