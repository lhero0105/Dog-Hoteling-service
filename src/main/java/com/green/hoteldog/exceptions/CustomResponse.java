package com.green.hoteldog.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CustomResponse {
    private final String codeNum;
    private final String code;
    private final LocalDateTime errorTimeStamp = LocalDateTime.now();
    private final String message;


    // Errors가 없다면 응답이 내려가지 않게 처리
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> valid;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        // @Valid 로 에러가 들어왔을 때, 어느 필드에서 에러가 발생했는 지에 대한 응답 처리
        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}