package ru.app.restapiservice.model.dto.error;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDtoView {

    private String fieldName;
    private String errorMessage;

    public Map<String, String> getError() {

        Map<String, String> errors = new HashMap<>();

        errors.put(this.fieldName, this.errorMessage);

        return errors;
    }
}
