package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "호텔 정보 + 리뷰<br>",description = "호텔 상세페이지에 뿌릴<br>" +
        "호텔 정보.(방 정보,옵션 포함.)")
//승준
public class HotelInfoVo {
    @NotNull
    @JsonProperty(value = "hotel_nm")
    @Schema(name = "호텔 이름")
    private String hotelNm;

    @NotNull
    @JsonProperty(value = "hotel_detail_info")
    @Schema(name = "호텔 상세정보")
    private String hotelDetailInfo;

    @NotNull
    @JsonProperty(value = "buisiness_num")
    @Schema(name = "사장님의 비밀스런 사업자번호")
    private String buisinessNum;

    @NotNull
    @JsonProperty(value = "hotel_call")
    @Schema(name = "호텔 전화번호")
    private String hotelCall;

    @NotNull
    @JsonProperty(value = "road_address")
    @Schema(name = "호텔 도로명주소")
    private String roadAddress;

    @JsonProperty(value = "address_detail")
    @Schema(name = "호텔 상세주소")
    private String addressDetail;

    @Schema(name = "호텔 사진")
    private List<String> pics;

    @NotNull
    @JsonProperty(value = "hotel_option")
    @Schema(name = "호텔 옵션")
    private List<String> hotelOption;

    @NotNull
    @Schema(name = "호텔 방 정보 리스트.")
    private List<HotelRoomInfoVo> roomList;

    @Schema(name = "유저들이 정성스럽게 쓴 호텔 리뷰")
    private List<HotelReviewVo> reviewList;

    @Schema(name = "리뷰 더있니?",description = "리뷰 더 없음=0,리뷰 더 있음=1"
            ,defaultValue = "0")
    private int isMoreReview=0;

}


