package com.green.hoteldog.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST,"400","Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND,"404","Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"500", "Internal server error");

    private final HttpStatus httpStatus;
    private final String errorNum;
    private final String message;
}
