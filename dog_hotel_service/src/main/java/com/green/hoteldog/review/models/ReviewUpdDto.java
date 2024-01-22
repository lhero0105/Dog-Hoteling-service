  package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReviewUpdDto {
    @JsonIgnore
    private int userPk;
    @JsonIgnore
    private List<MultipartFile> pics;
    private int reviewPk;
    private int resPk;
    private String comment;
    private int score;
}
