package com.green.hoteldog.review.models;

import lombok.Data;

import java.util.List;

@Data
public class UserReviewVo {
    private int reviewPk;
    private List<String> pics;
    private List<String> roomNm;
    private int score;
    private String comment;
    private String hotelNm;
    private String createdAt;
}
