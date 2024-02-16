package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class DeleteBoardDto {
    @JsonIgnore
    private int userPk;
    @NotEmpty(message = "입력된 값이 없습니다.")
    @Schema(description = "게시판pk 리스트")
    private List<Integer> boardPkList;
}
