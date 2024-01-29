package com.green.hoteldog.board.models;

import lombok.Data;

import java.util.List;

@Data
public class GetUserCommentVo {
    private int maxPage;
    private List<UserCommentVo> userCommentVoList;
}
