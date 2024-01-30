package com.green.hoteldog.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HotelErrorCode implements ErrorCode {
    UNKNOWN_DATE_FORM(HttpStatus.BAD_REQUEST,"날짜를 형식에 맞게 입력 해주세요.");


    private final HttpStatus httpStatus;
    private final String message;
}