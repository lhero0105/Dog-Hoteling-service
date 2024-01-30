package com.green.hoteldog.board.models;

import lombok.Data;

@Data
public class UserCommentVo {
    private int boardPk;
    private String title;
    private int boardCategoryPk;
    private String categoryNm;
    private String comment;
    private int userPk;
    private String nickname;
    private String createdAt;
    private int boardViewCount;
}
