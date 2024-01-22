package com.green.hoteldog.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

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