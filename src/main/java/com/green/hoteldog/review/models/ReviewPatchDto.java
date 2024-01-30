package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ReviewPatchDto {
    @JsonIgnore
    private int userPk;
    private int resPk;
    private String comment;
}
