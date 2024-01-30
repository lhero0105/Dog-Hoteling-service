package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReviewFavDto {
    @JsonProperty(value = "user_pk")
    private int userPk;
    @JsonProperty(value = "review_pk")
    private int reviewPk;
}
