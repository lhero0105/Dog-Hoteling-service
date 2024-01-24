package com.green.hoteldog.hotel;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.user.models.UserHotelFavDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService service;
    // 0-1 광고+호텔 리스트 api/hotel/{page}
    // 0-2 광고 리스트 api/hotel/ad

    //----------------------------------------------- 호텔 광고 리스트-----------------------------------------------------
    // 새로고침 시 적용
    @GetMapping("/ad")
    public List<HotelListSelVo> getHotelAdvertiseList(HotelListSelDto dto){
        return service.getHotelAdvertiseList(dto);
    }

    // 광고 + 호텔 리스트
    // 1. 첫 화면 뿌릴 때(유저 별 검색 별 필터링 별로 출력은 서비스에서 구현) 적용
    // 2. 페이지 전환 시 적용

    // 메인 페이지 호텔 리스트 셀렉트 : service단에서 정의
    // 1. 강아지 정보와 주소를 기반으로 첫 기본 화면 셀렉트
    // 1-1. 비회원은 최신순으로 첫 화면 셀렉트
    // 1-2. 회원은 등록한 정보 기반으로 셀렉트
    // 1-2-1. 주소만으로 셀렉트
    // 1-2-1. 주소와 강아지 정보로 셀렉트
    // 2. 필터링 처리 후 화면 셀렉트
    // 3. 검색 기반으로 화면 셀렉트 - OKT로 형태소 분석

    // 상세 정렬방식 - 리뷰 많은 순, 별점 높은 순 : XML에서 정의
    //--------------------------------------------------호텔 리스트-------------------------------------------------------
    @GetMapping("/{page}")
    public HotelListSelAllVo getHotelList(@RequestParam int page, HotelListSelDto dto) {
        dto.setRowCount(Const.HOTEL_LIST_COUNT_PER_PAGE);
        dto.setPage(page);
        return service.getHotelList(dto);
    }
    //영웅

    //-------------------------------------------------호텔 상세페이지 출력-------------------------------------------------
    @GetMapping("/a")
    public HotelInfoEntity getHotelDetail(@RequestBody HotelMainPageDto dto){
        if(dto.getHotelPk()==0){
            //예외
        }
        HotelInfoEntity mainPage=service.getHotelDetail(dto);
        return mainPage;
    }
    //------------------------------------------호텔 상세페이지에서 날짜 선택했을때--------------------------------------------
    @GetMapping("/c")
    public List<HotelRoomEaByDate> whenYouChooseDates(@RequestParam int hotelPk,String startDate,String endDate){
        return service.whenYouChooseDates(hotelPk, startDate, endDate);
    }
    //--------------------------------------호텔 상세페이지에서 날짜 선택, 강아지 선택했을때-------------------------------------
    @GetMapping("/d")
    public List<HotelRoomEaByDate> whenYouChooseDatesAndDogs(@RequestParam int hotelPk,String startDate,String endDate,List<Integer> dogs){
        return service.whenYouChooseDatesAndDogs(hotelPk, startDate, endDate, dogs);
    }

    //-----------------------------------------------------호텔 북마크----------------------------------------------------
    @GetMapping("/hotel/{page}/mark")
    @Operation(summary = "좋아요 toggle", description = "toggle로 처리함<br>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 처리: result(1), 좋아요 취소: result(2)")
    })
    public ResVo toggleHotelBookMark(@RequestBody UserHotelFavDto dto){
        return service.toggleHotelBookMark(dto);
    }
    //승준




}
