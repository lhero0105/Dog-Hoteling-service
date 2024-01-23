package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DogSizeEa {
    @JsonProperty(value = "dog_size")
    private int dogSize;
    @JsonProperty(value = "dog_count")
    private int dogCount;
}
