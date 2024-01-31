package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class HotelListSelDto {
    // @JsonProperty(required = false) 기본
    @Min(0)@Max(1)@Positive
    @JsonProperty(value = "main_filter")
    private int mainFilter; // 필터 했는지 유무
    private String address;
    @Future
    @JsonProperty(value = "from_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fromDate;
    @Future
    @JsonProperty(value = "to_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String toDate;
    @JsonProperty(value = "dog_info")
    private List<DogSizeEa> dogInfo = new ArrayList<>();
    private String search; // 호텔 이름 검색
    @Min(0)
    @JsonProperty(required = true, value = "row_count") @Positive
    private int rowCount;
    // 호텔 이용 테마
    @JsonProperty(value = "hotel_option_pk")
    private List<Integer> hotelOptionPk;
    @Min(0)@Max(2)@Positive
    @JsonProperty(value = "filter_type")
    private int filterType; // default(0) : 적용 x, 1 : 별점 높은 순, 2 : 리뷰 많은 순
    @JsonIgnore
    private int userPk;
    @JsonIgnore
    private List<LocalDate> date; // 기간을 날짜 리스트로 저장
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int page;
    @JsonIgnore
    private List<String> tokensToStrList;
    @JsonIgnore
    private int optionPkSize; // 옵션pk 총 갯수 - xml having절에서 사용
    @JsonIgnore
    private int dogPkSize; // 강아지pk 총 갯수 - xml having절에서 사용
    @JsonIgnore
    private List<Integer> filteredPk;

    public void setPage(int page){
        startIdx = (page - 1) * this.rowCount;
        this.page = page;
    }
}
