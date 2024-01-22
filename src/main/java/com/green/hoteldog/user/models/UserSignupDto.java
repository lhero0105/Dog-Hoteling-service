package com.green.hoteldog.user.models;

import com.green.hoteldog.email.EmailResponseVo;
import lombok.Data;

@Data
public class UserSignupDto {
    private int userTypePk;
    private String userEmail;
    private String upw;
    private String nickname;
    private String nm;
    private String phoneNum;
    private String userAddress;
    private EmailResponseVo emailResponseVo;
}
