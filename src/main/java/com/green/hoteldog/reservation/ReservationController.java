package com.green.hoteldog.reservation;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.reservation.model.HotelReservationInsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    private final ReservationService service;


    // 호텔 예약
    @PostMapping("/hotel/{hotel_pk}/res")
    public ResVo postHotelReservation(@RequestBody HotelReservationInsDto dto, @RequestParam ("hotel_pk") int hotelPk){
        dto.setHotelPk(hotelPk);
        return service.postHotelReservation(dto);
    }
}
