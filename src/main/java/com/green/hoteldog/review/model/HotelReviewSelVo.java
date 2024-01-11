package com.green.hoteldog.review.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class HotelReviewSelVo {
    private String nickName; // 유저
    private String userPic; // 유저
    private int reviewPk; // 리뷰
    private String comment; // 리뷰
    private int score; // 리뷰
    private String createdAt; // 리뷰
    private int favCount; // 좋아요
    private List<String> pics = new ArrayList<>(); // 사진
}
