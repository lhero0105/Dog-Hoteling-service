package com.green.hoteldog.review.models;

import lombok.Data;

import java.util.List;

@Data
public class ReviewInsPicsDto {
    private int reviewPk;
    private List<String> pics;
}
