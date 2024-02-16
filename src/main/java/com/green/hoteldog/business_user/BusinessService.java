package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.ReservaionListSelVo;
import com.green.hoteldog.common.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {

    // 호텔 삭제
    public ResVo delHotel(){
        return new ResVo(0);
    }

    // 광고 신청
    public ResVo postHotelAdvertiseApplication(int hotelPk){
        return new ResVo(0);
    }

    // 호텔 방 등록
    public ResVo postHotelRoom(){
        return new ResVo(0);
    }

    // 예약 리스트 출력
    public List<ReservaionListSelVo> getHotelReservationList(){
        return null;
    }
    // 영웅
}
