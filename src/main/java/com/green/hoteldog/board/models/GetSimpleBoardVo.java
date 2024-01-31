package com.green.hoteldog.board.models;

import lombok.Data;

import java.util.List;

@Data
public class GetSimpleBoardVo {
    private int maxPage;
    private List<SimpleBoardVo> simpleBoardVoList;
}
