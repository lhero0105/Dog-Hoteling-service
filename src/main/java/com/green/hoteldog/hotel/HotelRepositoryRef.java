package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.*;
import java.util.List;

public interface HotelRepositoryRef {
    List<HotelListSelVo> selHotelAdvertiseList();
    List<HotelListSelVo> selHotelListToNonMember(HotelListSelDto dto);
    List<HotelListSelProcDto> selUserInfoToUserPk(HotelListSelDto dto);
    List<HotelListSelVo> selHotelListAsUserAddress(HotelListSelDto pDto);
    List<HotelListSelVo> selHotelListAsUserAddressAndDogInformation(HotelListSelDto pDto);
    List<HotelListSelVo> selHotelListToSearch(HotelListSelDto Dto);
    List<HotelListSelVo> selHotelListToAccurateSearch(HotelListSelDto Dto);
    List<HotelListSelVo> selHotelListToFilter(HotelListSelDto Dto);
    List<Integer> selHotelPkToIndividualDogInfo(DogSizeInfoIn i);
    List<Integer> selHotelPkToGroupDogInfo(DogSizeInfoGr i);
    List<HotelReviewVo> getHotelReviewThree(int hotelPk);
    HotelInfoVo getHotelDetail(int hotelPk);
    List<String> getHotelPics(int hotelPk);
    List<HotelOptionInfoVo> hotelOptionInfo(int hotelPk);
    List<MyDog> getMyDogs(int userPk);
    List<HotelRoomInfoVo> getHotelRoomInfo(int hotelPk);
    Integer delHotelBookMark(int userPk,int hotelPk);
    Integer insHotelBookMark(int userPk,int hotelPk);
    List<HotelBookMarkListVo> getHotelBookMark(int userPk,int pages,int perPage);
    List<HotelBookMarkPicVo> getHotelBookMarkPic(List<Integer> pkList);
    Integer isMoreHotelReview(int hotelPk);
    List<HotelRoomResInfoByMonth> getHotelRoomResInfo(int hotelPk, String startDate, String endDate);
    List<HotelRoomResInfoByMonth> getHotelFilterRoomResInfo(int hotelPk,String startDate, String endDate,int howMany,int large );
    Integer insHotel(HotelInsDto dto);
    Integer insHotelWhere(HotelAddressDto dto);
    Integer insHotelPics(HotelInsPicDto dto);
    Integer delHotelPic(HotelInsPicDto dto);
    Integer insHotelOption(HotelInsDto dto);
    Integer insHotelRoomInfo(InsHotelRoomDto dto);
    Integer insHotelRoomInfoDate(InsHotelRoomDateInfoDto dto);
}
