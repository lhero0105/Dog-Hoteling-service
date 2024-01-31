package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReviewInsDto {
    @JsonIgnore
    private int reviewPk;
    @JsonIgnore
    private int userPk;
    @JsonProperty(value = "res_pk")
    private int resPk;
    private String comment;
    private int score;
    private List<MultipartFile> pics;
}
