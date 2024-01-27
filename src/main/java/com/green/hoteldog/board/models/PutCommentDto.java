package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PutCommentDto {
    @JsonIgnore
    private int userPk;
    @Min(value = 1,message = "commentPk 값은 최소 1 이어야 합니다")
    private int commentPk;
    @NotEmpty(message = "comment는 필수 입력값 입니다.")
    private String comment;
}
