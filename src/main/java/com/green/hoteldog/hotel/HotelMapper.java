package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.user.models.UserHotelFavDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelListSelVo> selHotelListToNonMember(HotelListSelDto dto);
    HotelListSelProcDto selUserInfoToUserPk(HotelListSelDto dto);
    List<HotelListSelVo> selHotelListToMember(HotelListSelProcDto pDto);
    List<HotelListSelVo> selHotelListToSearch(HotelListSelDto Dto);
    List<HotelListSelVo> selHotelListToFilter(HotelListSelDto Dto);
    //영웅

    List<HotelReviewVo> getHotelReviewThree(int hotelPk);
    HotelInfoVo getHotelDetail(int hotelPk);
    List<String> getHotelPics(int hotelPk);
    List<String> hotelOptionInfo(int hotelPk);
    List<MyDog> getMyDogs(int userPk);
    List<String> getReviewPics(HotelReviewDto dto);
    int delHotelBookMark(UserHotelFavDto dto);
    int insHotelBookMark(UserHotelFavDto dto);
    Integer isMoreHotelReview(int hotelPk);
    List<HotelRoomResInfoByMonth> getHotelRoomResInfo(int hotelPk, String startDate, String endDate);
    List<HotelRoomResInfoByMonth> getHotelFilterRoomResInfo(int hotelPk,String startDate, String endDate,int howMany,int large );
    //승준
}