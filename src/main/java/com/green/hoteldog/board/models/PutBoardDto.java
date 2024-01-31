package com.green.hoteldog.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PutBoardDto {
    @JsonIgnore
    private int userPk;
    @Min(value = 1,message = "boardPk 값은 최소 1 이어야 합니다.")
    private int boardPk;
    @Min(value = 1,message = "boardCategoryPk 값은 최소 1 이어야 합니다.")
    private int boardCategoryPk;
    @NotEmpty(message = "title은 필수 입력값 입니다.")
    private String title;
    @NotEmpty(message = "contetns값은 필수 입력값 입니다.")
    private String contents;
    @JsonIgnore
    private List<MultipartFile> pics;
}
