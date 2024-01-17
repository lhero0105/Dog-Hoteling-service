package com.green.hoteldog.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
//영웅
@Builder
@Getter
public class HotelReviewSelVo {
    private String nickName;
    private String userPic;
    private int reviewPk;
    private String comment;
    private int score;
    private String createdAt;
    private int favCount;
    private List<String> pics = new ArrayList<>(); // 사진
}
