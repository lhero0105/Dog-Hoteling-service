package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DelReviewDto {
    @JsonIgnore
    private int userPk;
    private int reviewPk;
    private int resPk;
}
