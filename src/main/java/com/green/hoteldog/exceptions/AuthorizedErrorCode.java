package com.green.hoteldog.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthorizedErrorCode implements ErrorCode{
    NOT_AUTHORIZED(HttpStatus.INTERNAL_SERVER_ERROR,"로그인 후 이용 가능합니다");

    private final HttpStatus httpStatus;
    private final String message;
}
