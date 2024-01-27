package com.green.hoteldog.board.models;

import lombok.Data;

import java.util.List;

@Data
public class GetBoardInfoVo {
    private int boardPk;
    private String title;
    private int userPk;
    private String nickname;
    private String createdAt;
    private int boardViewCount;
    private List<String> pics;
    private List<CommentInfoVo> comments;
}
