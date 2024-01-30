package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.user.models.UserHotelFavDto;
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
    List<Integer> selHotelPkToIndividualDogInfo(DogSizeInfoIn i);
    List<Integer> selHotelPkToGroupDogInfo(DogSizeInfoGr i);
    //영웅

    List<HotelReviewVo> getHotelReviewThree(int hotelPk);
    HotelInfoVo getHotelDetail(int hotelPk);
    List<String> getHotelPics(int hotelPk);
    List<String> hotelOptionInfo(int hotelPk);
    List<MyDog> getMyDogs(int userPk);
    List<HotelRoomInfoVo> getHotelRoomInfo(int hotelPk);
    int delHotelBookMark(UserHotelFavDto dto);
    int insHotelBookMark(UserHotelFavDto dto);
    List<HotelBookMarkListVo> getHotelBookMark(int userPk);
    Integer isMoreHotelReview(int hotelPk);
    List<HotelRoomResInfoByMonth> getHotelRoomResInfo(int hotelPk, String startDate, String endDate);
    List<HotelRoomResInfoByMonth> getHotelFilterRoomResInfo(int hotelPk,String startDate, String endDate,int howMany,int large );
    //승준

    int insHotel(HotelInsDto dto);
    int insHotelWhere(HotelAddressDto dto);
    int insHotelPics(HotelInsPicDto dto);
    int delHotelPic(HotelInsPicDto dto);
    int insHotelOption(HotelInsDto dto);

    int insHotelRoomInfo(InsHotelRoomDto dto);
    int insHotelRoomInfoDate(InsHotelRoomDateInfoDto dto);
}