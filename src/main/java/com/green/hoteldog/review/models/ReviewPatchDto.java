package com.green.hoteldog.review.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ReviewPatchDto {
    @JsonIgnore
    private int userPk;
    @JsonProperty(value = "res_pk")
    private int resPk;
    private String comment;
}
