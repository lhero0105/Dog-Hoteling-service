package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.ReservaionListSelVo;
import com.green.hoteldog.common.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class BusinessController {
    private final BusinessService service;

    // 호텔 삭제
    @DeleteMapping
    public  ResVo delHotel(){
        return service.delHotel();
    }

    // 광고 신청
    @GetMapping("/advertise")
    public ResVo postHotelAdvertiseApplication(int hotelPk){
        return service.postHotelAdvertiseApplication(hotelPk);
    }

    // 호텔 방 등록
    @PostMapping("/hotelRoom")
    public ResVo postHotelRoom(){
        return service.postHotelRoom();
    }

    // 예약 리스트 출력
    @GetMapping("/reservation")
    public List<ReservaionListSelVo> getHotelReservationList(){
        return service.getHotelReservationList();
    }
    // 영웅

}
