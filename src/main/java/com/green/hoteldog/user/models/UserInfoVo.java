package com.green.hoteldog.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfoVo {
    @JsonProperty(value = "user_pk")
    private int userPk;
    @JsonProperty(value = "user_email")
    private String userEmail;
    private String nickname;
    @JsonProperty(value = "phone_num")
    private String phoneNum;
    @JsonProperty(value = "user_address")
    private String userAddress;

}
