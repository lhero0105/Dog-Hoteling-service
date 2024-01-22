package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//영웅
@Data
public class HotelReviewSelDto {
    private int hotelPk;
    private int startIdx;
    private int rowCount;
    @JsonIgnore
    private List<Integer> reviewPk = new ArrayList<>();
    public void setPage(int page){
        this.startIdx = (page - 1) * rowCount;
    }
}