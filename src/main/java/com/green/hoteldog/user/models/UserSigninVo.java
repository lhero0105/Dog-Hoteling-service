package com.green.hoteldog.user.models;

import lombok.Data;

@Data
public class UserSigninVo {
    private int userPk;
    private int userTypePk;
    private String nickname;
    private String accessToken;
}
