package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ReviewFavDto {
    @JsonIgnore
    private int userPk;
    private int reviewPk;
}
