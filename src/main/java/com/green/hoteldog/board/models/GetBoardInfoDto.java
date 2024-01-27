package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class GetBoardInfoDto {
    @Min(value = 1,message = "boardPk 값은 1 이상이어야 합니다.")
    private int boardPk;
    private int page;
    @Min(value = 1,message = "rowCount 값은 1 이상이어야 합니다.")
    private int rowCount;
    @JsonIgnore
    private int startIdx;

    public void setPage(int page){
        this.startIdx = (page - 1) * this.rowCount;
    }
}
