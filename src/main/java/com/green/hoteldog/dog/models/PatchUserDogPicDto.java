package com.green.hoteldog.dog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PatchUserDogPicDto {
    @JsonIgnore
    private int userPk;
    @JsonIgnore
    private MultipartFile pic;
    private int userDogPk;
}
