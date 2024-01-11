package com.green.hoteldog.reservation;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.reservation.model.DogInfo;
import com.green.hoteldog.reservation.model.HotelReservationInsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper mapper;

    public ResVo postHotelReservation(HotelReservationInsDto dto){
        log.info("dto : {}", dto);
        Integer resPk = mapper.insHotelReservation(dto);
        if (resPk == null){
            return new ResVo(Const.FAIL);
        }
        dto.setResPk(resPk);
        List<DogInfo> list = new ArrayList<>();
        for (int i = 0; i < dto.getDogInfo().size(); i++) {

        }
        int affectedRows = mapper.insHotelReservationDogInfo(dto);
        log.info("");
        if (resPk <= 0){
            return new ResVo(Const.FAIL);
        }

        return new ResVo(Const.SUCCESS);
    }
}
