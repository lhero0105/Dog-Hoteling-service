package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BoardFavDto {
    @JsonIgnore
    private int userPk;
    @Min(value = 1,message = "boardPk 값은 최소 1 이어야 합니다.")
    private int boardPk;
}
