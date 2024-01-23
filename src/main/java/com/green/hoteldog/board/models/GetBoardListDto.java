package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GetBoardListDto {
    @Min(value = 1,message = "boardCategoryPk 값은 1 이상이어야 합니다.")
    private int boardCategoryPk;
    @Min(value = 1,message = "page 값은 1 이상이어야 합니다.")
    private int page;
    @Min(value = 1,message = "rowCount 값은 1 이상이어야 합니다.")
    private int rowCount;
    @Size(min = 2,message = "검색은 최소 2글자를 입력해야 합니다.")
    private String search;
    @Min(value = 0,message = "searchType 값은 0 이상이어야 합니다.")
    private int searchType;
    @JsonIgnore
    private int startIdx;

    public void setPage(int page){
        this.startIdx = (page - 1) * this.rowCount;
    }
}
