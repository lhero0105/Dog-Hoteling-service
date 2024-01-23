package com.green.hoteldog.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    UNKNOWN_EMAIL_ADDRESS(HttpStatus.NOT_FOUND,"등록되지 않은 이메일입니다."),
    MISS_MATCH_PASSWORD(HttpStatus.BAD_REQUEST,"비밀번호를 확인 해 주세요");


    private final HttpStatus httpStatus;
    private final String message;
}
