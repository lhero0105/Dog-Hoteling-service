package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class DeleteBoardDto {
    @JsonIgnore
    private int userPk;
    private List<Integer> boardPkList;
}
