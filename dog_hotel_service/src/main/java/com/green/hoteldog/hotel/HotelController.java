package com.green.hoteldog.hotel;

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

    // 메인 페이지 호텔 리스트 셀렉트
    // 1. 강아지 정보와 주소를 기반으로 첫 기본 화면 셀렉트
    // 2. 필터링을 처리 후 화면 셀렉트
    // 3. 검색 기반으로 화면 셀렉트

    // 0-1 광고+호텔 리스트 api/hotel/{page}
    // 0-3 광고 리스트 api/hotel/ad

    // 호텔 광고 리스트
    // 새로고침 시 적용
    @GetMapping("/ad")
    public List<HotelListSelVo> getHotelAdvertiseList(HotelListSelDto dto){
        return service.getHotelAdvertiseList(dto);
    }

    // 광고 + 호텔 리스트
    // 1. 첫 화면 뿌릴 때(유저 별 검색 별 필터링 별로 출력은 서비스에서 구현)
    // 2. 페이지 전환 시
    @GetMapping("/{page}")
    public HotelListSelAllVo getHotelList(@RequestParam int page, HotelListSelDto dto){
        dto.setRowCount(1); //수정요망.
        dto.setPage(page);
        return service.getHotelList(dto);
    }
    //영웅

    @GetMapping()
    public HotelMainPage getHotelDetail(@RequestBody HotelMainPageDto dto){
        if(dto.getHotelPk()==0){
            //예외
        }
        HotelMainPage mainPage=service.getHotelDetail(dto);
        return mainPage;
    }
    @GetMapping
    public List<HotelRoomEaByDate> whenYouChooseDates(@RequestParam int hotelPk,String startDate,String endDate){
        return service.whenYouChooseDates(hotelPk, startDate, endDate);
    }
    @GetMapping
    public List<HotelRoomEaByDate> whenYouChooseDatesAndDogs(@RequestParam int hotelPk,String startDate,String endDate,List<Integer> dogs){
        return service.whenYouChooseDatesAndDogs(hotelPk, startDate, endDate, dogs);
    }

    @GetMapping("/hotel/{page}/mark")
    @Operation(summary = "좋아요 toggle", description = "toggle로 처리함<br>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 처리: result(1), 좋아요 취소: result(2)")
    })
    public ResVo toggleHotelBookMark(@RequestBody UserHotelFavDto dto){
        return service.toggleHotelBookMark(dto);
    }
    //승준



    // 브랜치 작업 하는법
    /*
    깃허브에서 브랜치 생성, 그 브랜치에서 작업.
    좌상단 Remote >브랜치 이름 확인하고 커밋&푸쉬
    좌상단 브랜치 Remote > master > Merge "master" into "자기이름" 클릭.


        깃허브 Comparing changes 접속. base: master , compare : "자기 브랜치 이름" 으로 설정.

         오른쪽 created pull request 클릭

         변경사항 작성 후 pull

      ..

      받을려는 사람은 Merge pull request 클릭해야 받아짐ㅋ.

     */
    //수정사항

}
