package com.green.hoteldog.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data

public class UserSignUpDto {
    @JsonIgnore
    private int userPk;
    private int userTypePk;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
    private String createdAt;
    private String nickName;
    private String phoneNum;



}
