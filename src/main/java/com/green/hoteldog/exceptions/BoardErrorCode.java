package com.green.hoteldog.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {
    PICS_SIZE_OVER(HttpStatus.BAD_REQUEST,"사진은 최대 3장까지만 등록됩니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
