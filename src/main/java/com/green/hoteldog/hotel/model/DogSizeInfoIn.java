package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DogSizeInfoIn {
    private int dogSize;
    private int dogCount;
    private List<LocalDate> dates;
}
