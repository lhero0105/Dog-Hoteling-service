package com.green.hoteldog.dog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetDogListVo {
    @JsonProperty(value = "user_dog_pk")
    private int userDogPk;
    @JsonProperty(value = "size_pk")
    private int sizePk;
    @JsonProperty(value = "dog_size")
    private String dogSize;
    @JsonProperty(value = "dog_nm")
    private String dogNm;
    @JsonProperty(value = "dog_age")
    private int dogAge;
    @JsonProperty(value = "dog_pic")
    private String dogPic;
    @JsonProperty(value = "dog_etc")
    private String dogEtc;
    @JsonProperty(value = "created_at")
    private String createdAt;
}
