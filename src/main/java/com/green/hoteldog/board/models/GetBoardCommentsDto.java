package com.green.hoteldog.board.models;

import lombok.Data;

@Data
public class GetBoardcommentDto {
    private int boardPk;
    private int page;
    private int rowCount;

    public void setPage(int page){
        this.rowCount = (page - 1) * this.rowCount;
    }
}
