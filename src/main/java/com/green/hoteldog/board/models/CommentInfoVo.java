package com.green.hoteldog.board.models;

import lombok.Data;

@Data
public class CommentInfoVo {
    private int commentPk;
    private int userPk;
    private String userNickname;
    private String comment;
    private String createdAt;
}
