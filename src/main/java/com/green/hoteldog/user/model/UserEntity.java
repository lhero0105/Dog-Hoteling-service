package com.green.hoteldog.user.model;

import lombok.Data;

@Data
public class UserEntity {
    private int userPk;
    private int userTypePk;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
    private String createdAt;
    private String updatedAt;
    private String nickName;
    private String phoneNum;

}
