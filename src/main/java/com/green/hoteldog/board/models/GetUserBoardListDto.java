package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.hoteldog.common.Const;
import lombok.Data;

@Data
public class GetUserBoardListDto {
    @JsonIgnore
    private int userPk;
    private int page;
    private int rowCount = Const.BOARD_COUNT_PER_PAGE;
    private int startIdx;

    public void setStartIdx(int startIdx){
        this.startIdx = (this.page - 1) * this.rowCount;
    }
}
