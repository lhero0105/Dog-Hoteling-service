package com.green.hoteldog.review.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//영웅
@Builder
@Getter
@Setter
public class HotelReviewSelVo {
    private String nickName;
    //private String userPic; // 유저사진 기획변경
    private int reviewPk;
    private String comment;
    private int score;
    private String createdAt;
    private int favCount;
    private List<String> pics = new ArrayList<>(); // 사진
}
