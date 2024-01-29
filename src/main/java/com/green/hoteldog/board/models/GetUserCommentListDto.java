package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.hoteldog.common.Const;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class GetUserCommentListDto {
    @JsonIgnore
    private int userPk;
    @Min(value = 1,message = "page 값은 1 이상만 입력 가능합니다.")
    private int page;
    @JsonIgnore
    private int rowCount = Const.BOARD_COUNT_PER_PAGE;
    @JsonIgnore
    private int startIdx;

    public void setStartIdx(int startIdx){
        this.startIdx = (this.page - 1) * this.rowCount;
    }
}
