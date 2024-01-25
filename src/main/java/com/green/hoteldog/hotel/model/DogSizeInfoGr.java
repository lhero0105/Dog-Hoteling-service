package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DogSizeInfoGr {
    private int allDogCount;
    private int biggestDogSize;
    private List<LocalDate> date;
}
