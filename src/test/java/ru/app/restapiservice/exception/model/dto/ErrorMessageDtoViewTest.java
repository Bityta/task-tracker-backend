package ru.app.restapiservice.exception.model.dto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class ErrorMessageDtoViewTest {

    @Test
    void testGetError_ReturnsCorrectMap() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String fieldName = "fieldName";
        String error = "Error message";
        String path = "/api/endpoint";

        ErrorMessageDtoView errorMessageDtoView = ErrorMessageDtoView.builder()
                .status(status)
                .fieldName(fieldName)
                .error(error)
                .path(path)
                .timestamp("2022-01-01 12:00:00")
                .build();

        Map<String, String> errorMap = errorMessageDtoView.getError();

        assertThat(errorMap).containsEntry("status", String.valueOf(status));
        assertThat(errorMap).containsEntry("error", error);
        assertThat(errorMap).containsEntry("path", path);
    }

    @Test
    void testGetError_UsesCurrentTimestampIfNotProvided() {
        ErrorMessageDtoView errorMessageDtoView = ErrorMessageDtoView.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .error("Internal server error")
                .path("/api/endpoint")
                .build();

        Map<String, String> errorMap = errorMessageDtoView.getError();

        assertThat(errorMap).containsKey("timestamp");
    }

    @Test
    void testGetError_ReturnsCorrectErrorMap() {
        ErrorMessageDtoView errorMessageDtoView = ErrorMessageDtoView.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("Error message")
                .path("/api/endpoint")
                .build();

        Map<String, String> errorMap = errorMessageDtoView.getError();

        assertThat(errorMap).containsEntry("status", "400 BAD_REQUEST");
        assertThat(errorMap).containsEntry("error", "Error message");
        assertThat(errorMap).containsEntry("path", "/api/endpoint");
    }

    @Test
    void testGetError_ReturnsErrorMapWithTimestamp() {
        ErrorMessageDtoView errorMessageDtoView = ErrorMessageDtoView.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("Error message")
                .path("/api/endpoint")
                .build();

        Map<String, String> errorMap = errorMessageDtoView.getError();

        assertThat(errorMap).containsKey("timestamp");
    }


    @Test
    void testNoArgsConstructor() {
        ErrorMessageDtoView errorMessage = new ErrorMessageDtoView();
        assertNotNull(errorMessage);
    }


}
