package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "호텔 옵션")
public class HotelOptionInfoVo {
    @JsonProperty(value = "option_nm")
    private String optionNm;
    @JsonIgnore
    private int optionPk;
}
