package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class HotelReviewSelVo {
    @JsonProperty(value = "nick_name")
    private String nickName;
    @JsonProperty(value = "review_pk")
    private int reviewPk;
    private String comment;
    private int score;
    @JsonProperty(value = "created-at")
    private String createdAt;
    @JsonProperty(value = "fav_count")
    private int favCount;
    private List<String> pics = new ArrayList<>(); // 사진
}
