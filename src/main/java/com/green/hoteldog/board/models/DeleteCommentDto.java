package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class DeleteCommentDto {
    @JsonIgnore
    private int userPk;
    private List<Integer> commentPkList;
}
