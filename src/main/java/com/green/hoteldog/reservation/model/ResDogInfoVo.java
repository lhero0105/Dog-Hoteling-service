package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResDogInfoVo {
    @JsonProperty(value = "res_pk")
    private int resPk;
    @JsonProperty(value = "dot_nm")
    private String dogNm;
}
