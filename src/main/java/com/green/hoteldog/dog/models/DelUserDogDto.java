package com.green.hoteldog.dog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DelUserDogDto {
    @JsonIgnore
    private int userPk;
    @Schema(description = "유저강아지 pk")
    private int userDogPk;
}
