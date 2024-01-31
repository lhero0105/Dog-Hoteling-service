package com.green.hoteldog.board.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class GetUserCommentVo {
    @Schema(description = "최대 페이지")
    private int maxPage;
    private List<UserCommentVo> userCommentVoList;
}
