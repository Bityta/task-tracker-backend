package ru.app.restapiservice.api.model.dto.email;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDto {

    private String email;
}
