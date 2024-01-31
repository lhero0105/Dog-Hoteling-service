package com.green.hoteldog.dog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsUserDogDto {
    @JsonIgnore
    private int userDogPk;
    @JsonIgnore
    private int userPk;
    @JsonProperty(value = "size_pk")
    private int sizePk;
    @JsonProperty(value = "dog_nm")
    private String dogNm;
    @JsonProperty(value = "dog_age")
    private int dogAge;
    @JsonProperty(value = "dog_pic")
    private MultipartFile dogPic;
    @JsonProperty(value = "dog_etc")
    private String dogEtc;
}
