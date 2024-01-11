package com.green.hoteldog.review.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class HotelReviewSelDto {
    private int hotelPk;
    private int page;
    private int stardIdx;
    private int rowCount;
    public void setPage(int page){
        this.page = page;
        this.stardIdx = (page - 1) * rowCount;
    }
}