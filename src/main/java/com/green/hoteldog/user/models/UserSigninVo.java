package com.green.hoteldog.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserSigninVo {
    @JsonProperty(value = "user_pk")
    private int userPk;
    @JsonProperty(value = "user_type_pk")
    private int userTypePk;
    private String nickname;
    @JsonProperty(value = "access_token")
    private String accessToken;
    /*
    private String depthName; //유저 주소
    private List<Integer> sizePkList; //유저가 보유한 강아지 사이즈
    */
}
