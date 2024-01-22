package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class GetBoardListDto {
    private int boardCategoryPk;
    private int page;
    private int rowCount;
    @JsonIgnore
    private int startIdx;

    public void setPage(int page){
        this.startIdx = (page - 1) * this.rowCount;
    }
}
