package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PutBoardDto {
    @JsonIgnore
    private int userPk;
    private int boardPk;
    private int boardCategoryPk;
    private String title;
    private String contents;
    @JsonIgnore
    private List<MultipartFile> pisc;
}
