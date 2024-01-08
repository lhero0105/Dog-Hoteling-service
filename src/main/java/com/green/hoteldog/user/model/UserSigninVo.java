package com.green.hoteldog.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSigninVo {
    private int result;
    private String userPk;
    private String pic;
    private String nickName;

}
