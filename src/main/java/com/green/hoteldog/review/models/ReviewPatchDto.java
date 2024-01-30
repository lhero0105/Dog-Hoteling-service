package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReviewPatchDto {
    @JsonProperty(value = "user_pk")
    private int userPk;
    @JsonProperty(value = "res_pk")
    private int resPk;
    private String comment;
}
