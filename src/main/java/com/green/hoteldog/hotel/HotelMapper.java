package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.HotelListSelDto;
import com.green.hoteldog.hotel.model.HotelListSelProcDto;
import com.green.hoteldog.hotel.model.HotelListSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelListSelVo> selHotelListToNonMember(HotelListSelDto dto);
    HotelListSelProcDto selUserInfoToUserPk(HotelListSelDto dto);
    List<HotelListSelVo> selHotelListToMember(HotelListSelProcDto pDto);
    List<HotelListSelVo> selHotelListToSearch(HotelListSelDto Dto);
    List<HotelListSelVo> selHotelListToFilter(HotelListSelDto Dto);
}