package com.green.hoteldog.board.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class GetBoardInfoDto {
    @Min(value = 1,message = "boardPk 값은 1 이상이어야 합니다.")
    @Schema(description = "게시글pk",defaultValue = "1")
    private int boardPk;

}
