package com.green.hoteldog.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public MyUserDtails getLoginUser(){
        return (MyUserDtails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

    }
    public int getLoginUserPk(){
        MyUserDtails myUserDtails = getLoginUser();
        return myUserDtails == null ? 0 : myUserDtails.getMyprincipal().getUserPk();
    }
}
