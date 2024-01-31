package com.green.hoteldog.user.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserUpdateDto {
    @JsonIgnore
    private int userPk;
    private String nickname;
    @JsonProperty(value = "phone_num")
    private String phoneNum;
    @JsonProperty(value = "user_address")
    private String userAddress;
    @JsonProperty(value = "address_entity")
    private UserAddressEntity addressEntity;
}
