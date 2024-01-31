package com.green.hoteldog.reservation;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.exceptions.CommonErrorCode;
import com.green.hoteldog.exceptions.CustomException;
import com.green.hoteldog.reservation.model.HotelReservationDelDto;
import com.green.hoteldog.reservation.model.HotelReservationInsDto;
import com.green.hoteldog.reservation.model.ResInfoVo;
import com.green.hoteldog.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    private final ReservationService service;
    private final AuthenticationFacade authenticationFacade;
    public void checkUser(){
        if(authenticationFacade.getLoginUserPk()==0){
            throw new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }
    //---------------------------------------------------호텔 예약--------------------------------------------------------
    @PostMapping("/hotel/res")
    public ResVo postHotelReservation(@RequestBody List<HotelReservationInsDto> dto){
        return service.postHotelReservation(dto);
    }
    //---------------------------------------------------예약 취소--------------------------------------------------------
    @DeleteMapping("/hotel/res")
    public ResVo delHotelReservation(HotelReservationDelDto dto){
        return service.delHotelReservation(dto);
    }
    //-------------------------------------------------예약내역 출력-------------------------------------------------------
    @GetMapping("/reservation")
    public List<ResInfoVo> getUserReservation(int page){
        checkUser();
        int userPk= authenticationFacade.getLoginUserPk();
        return service.getUserReservation(userPk,page);
    }

}
