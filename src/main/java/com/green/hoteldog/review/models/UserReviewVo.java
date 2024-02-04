package com.green.hoteldog.review.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserReviewVo {
    private int reviewPk;
    private int resPk;
    private List<String> reviewPics = new ArrayList<>();
    private List<String> roomNm = new ArrayList<>();
    private int score;
    private String comment;
    private String hotelNm;
    private String createdAt;
}
