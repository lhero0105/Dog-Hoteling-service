package com.green.hoteldog.review.models;

import lombok.Data;

@Data
public class ReviewPatchDto {
    private int userPk;
    private int resPk;
    private String comment;
}
