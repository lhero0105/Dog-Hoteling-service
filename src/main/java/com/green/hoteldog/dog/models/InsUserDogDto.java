package com.green.hoteldog.dog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsUserDogDto {
    @JsonIgnore
    private int userDogPk;
    @JsonIgnore
    private int userPk;
    private int sizePk;
    private String dogNm;
    private int dogAge;
    private MultipartFile dogPic;
    private String dogEtc;
}
