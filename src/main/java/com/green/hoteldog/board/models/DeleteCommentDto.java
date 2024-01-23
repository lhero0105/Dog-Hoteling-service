package com.green.hoteldog.board.models;

import lombok.Data;

import java.util.List;

@Data
public class DeleteCommentDto {
    private int userPk;
    private List<Integer> commentPkList;
}
