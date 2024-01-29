package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.hoteldog.common.Const;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GetBoardListDto {
    @Min(value = 0,message = "boardCategoryPk 값은 0 이상이어야 합니다.")
    private int boardCategoryPk;
    private int page;
    @JsonIgnore
    private int rowCount = Const.BOARD_COUNT_PER_PAGE;
    @Size(min = 2,message = "검색은 최소 2글자를 입력해야 합니다.")
    private String search;
    @Min(value = 0,message = "searchType 값은 0 이상이어야 합니다.")
    private int searchType;
    @JsonIgnore
    private int startIdx;

    public void setStartIdx(int startIdx){
        this.startIdx = (this.page - 1) * this.rowCount;
    }
}
