package com.green.hoteldog.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {
    NOT_CHECK_OUT_STATUS(HttpStatus.BAD_REQUEST,"아직 체크아웃 한 예약이 아닙니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
