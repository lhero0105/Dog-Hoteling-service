package com.green.hoteldog.board.models;

import lombok.Data;

@Data
public class SimpleBoardVo {
    private int boardPk;
    private String title;
    private int userPk;
    private String nickname;
    private int boardViewCount;
    private String createdAt;
}