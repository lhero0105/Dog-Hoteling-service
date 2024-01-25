package com.green.hoteldog.email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmailCheckDto {
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@([a-zA-Z0-9]{3,}\\.[a-z]{2,3}|[a-zA-Z0-9]{3,}\\.[a-z]{2,3}\\.[a-z]{2,3})$",message = "올바른 이메일 형식이 아닙니다.")
    @NotEmpty(message = "이메일을 입력해 주세요")
    private String email;
    @NotEmpty(message = "인증번호를 입력해 주세요")
    private String authNum;
}