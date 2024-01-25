package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HotelListSelDto {
    // @JsonProperty(required = false) 기본
    @JsonIgnore
    private int userPk;
    private String address;
    @JsonProperty(value = "from_date")
    private String fromDate;
    @JsonProperty(value = "to_date")
    private String toDate;
    @JsonProperty(value = "dog_info")
    private List<DogSizeEa> dogInfo;
    @JsonIgnore
    private List<LocalDate> date; // 기간을 날짜 리스트로 저장
    private String search; // 호텔 이름 검색
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;
    @JsonIgnore
    private int page;
    // 호텔 이용 테마
    @JsonProperty(value = "hotel_option_pk")
    private List<Integer> hotelOptionPk;
    @JsonIgnore
    private List<String> tokensToStrList;
    @JsonProperty(value = "filter_type")
    private int filterType; // default(0) : 적용 x, 1 : 별점 높은 순, 2 : 리뷰 많은 순
    @JsonIgnore
    private int optionPkSize; // 옵션pk 총 갯수 - xml having절에서 사용
    @JsonIgnore
    private int dogPkSize; // 강아지pk 총 갯수 - xml having절에서 사용
    @JsonIgnore
    private List<Integer> filteredPk;

    public void setPage(int page){
        startIdx = (page - 1) * rowCount;
        this.page = page;
    }
}
