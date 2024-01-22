package com.green.hoteldog.hotel;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.HotelListSelAllVo;
import com.green.hoteldog.hotel.model.HotelListSelDto;
import com.green.hoteldog.hotel.model.HotelListSelVo;
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
        dto.setRowCount(Const.HOTEL_LIST_COUNT_PER_PAGE);
        dto.setPage(page);
        return service.getHotelList(dto);
    }

}
