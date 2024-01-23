package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.HotelListSelDto;
import com.green.hoteldog.hotel.model.HotelListSelProcDto;
import com.green.hoteldog.hotel.model.HotelListSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelListSelVo> selHotelAdvertiseList(HotelListSelDto dto);
    List<HotelListSelVo> selHotelListToNonMember(HotelListSelDto dto);
    HotelListSelProcDto selUserInfoToUserPk(HotelListSelDto dto);
    List<HotelListSelVo> selHotelListAsUserAddress(HotelListSelProcDto pDto);
    List<HotelListSelVo> selHotelListAsUserAddressAndDogInformation(HotelListSelProcDto pDto);
    List<HotelListSelVo> selHotelListToSearch(HotelListSelDto Dto);
    List<HotelListSelVo> selHotelListToAccurateSearch(HotelListSelDto Dto);
    List<HotelListSelVo> selHotelListToFilter(HotelListSelDto Dto);
    //영웅
}