package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DeleteCommentDto {
    @JsonIgnore
    private int userPk;
    private int commentPk;
}
