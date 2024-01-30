package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.hoteldog.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class GetUserBoardListDto {
    @JsonIgnore
    private int userPk;
    @Min(value = 1, message = "페이지 값은 최소 1이어야 합니다.")
    @Schema(description = "게시글 페이지 번호",defaultValue = "1")
    private int page;
    @JsonIgnore
    private int rowCount = Const.BOARD_COUNT_PER_PAGE;
    @JsonIgnore
    private int startIdx;

    public void setStartIdx(int startIdx){
        this.startIdx = (this.page - 1) * this.rowCount;
    }
}
