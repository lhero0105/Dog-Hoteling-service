package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class HotelRepository implements HotelRepositoryRef{
    private final HotelMapper hotelMapper;
    public List<HotelListSelVo> selHotelAdvertiseList(){
        return hotelMapper.selHotelAdvertiseList();
    }
    public List<HotelListSelVo> selHotelListToNonMember(HotelListSelDto dto) {
        return hotelMapper.selHotelListToNonMember(dto);
    }
    public List<HotelListSelProcDto> selUserInfoToUserPk(HotelListSelDto dto) {
        return hotelMapper.selUserInfoToUserPk(dto);
    }
    public List<HotelListSelVo> selHotelListAsUserAddress(HotelListSelDto pDto) {
        return hotelMapper.selHotelListAsUserAddress(pDto);
    }
    public List<HotelListSelVo> selHotelListAsUserAddressAndDogInformation(HotelListSelDto pDto) {
        return hotelMapper.selHotelListAsUserAddressAndDogInformation(pDto);
    }
    public List<HotelListSelVo> selHotelListToSearch(HotelListSelDto Dto) {
        return hotelMapper.selHotelListToSearch(Dto);
    }
    public List<HotelListSelVo> selHotelListToAccurateSearch(HotelListSelDto Dto) {
        return hotelMapper.selHotelListToAccurateSearch(Dto);
    }
    public List<HotelListSelVo> selHotelListToFilter(HotelListSelDto Dto) {
        return hotelMapper.selHotelListToFilter(Dto);
    }
    public List<Integer> selHotelPkToIndividualDogInfo(DogSizeInfoIn i) {
        return hotelMapper.selHotelPkToIndividualDogInfo(i);
    }
    public List<Integer> selHotelPkToGroupDogInfo(DogSizeInfoGr i) {
        return hotelMapper.selHotelPkToGroupDogInfo(i);
    }
    public List<HotelReviewVo> getHotelReviewThree(int hotelPk) {
        return hotelMapper.getHotelReviewThree(hotelPk);
    }
    public HotelInfoVo getHotelDetail(int hotelPk) {
        return hotelMapper.getHotelDetail(hotelPk);
    }
    public List<String> getHotelPics(int hotelPk) {
        return hotelMapper.getHotelPics(hotelPk);
    }
    public List<HotelOptionInfoVo> hotelOptionInfo(int hotelPk) {
        return hotelMapper.hotelOptionInfo(hotelPk);
    }
    public List<MyDog> getMyDogs(int userPk) {
        return hotelMapper.getMyDogs(userPk);
    }
    public List<HotelRoomInfoVo> getHotelRoomInfo(int hotelPk) {
        return hotelMapper.getHotelRoomInfo(hotelPk);
    }
    public Integer delHotelBookMark(int userPk, int hotelPk) {
        return hotelMapper.delHotelBookMark(userPk, hotelPk);
    }
    public Integer insHotelBookMark(int userPk, int hotelPk) {
        return hotelMapper.insHotelBookMark(userPk, hotelPk);
    }
    public List<HotelBookMarkListVo> getHotelBookMark(int userPk, int pages, int perPage) {
        return hotelMapper.getHotelBookMark(userPk, pages, perPage);
    }
    public List<HotelBookMarkPicVo> getHotelBookMarkPic(List<Integer> pkList) {
        return hotelMapper.getHotelBookMarkPic(pkList);
    }
    public Integer isMoreHotelReview(int hotelPk) {
        return hotelMapper.isMoreHotelReview(hotelPk);
    }
    public List<HotelRoomResInfoByMonth> getHotelRoomResInfo(int hotelPk, String startDate, String endDate) {
        return hotelMapper.getHotelRoomResInfo(hotelPk, startDate, endDate);
    }
    public List<HotelRoomResInfoByMonth> getHotelFilterRoomResInfo(int hotelPk, String startDate, String endDate, int howMany, int large) {
        return hotelMapper.getHotelFilterRoomResInfo(hotelPk, startDate, endDate, howMany, large);
    }
    public Integer insHotel(HotelInsDto dto) {
        return hotelMapper.insHotel(dto);
    }
    public Integer insHotelWhere(HotelAddressDto dto) {
        return hotelMapper.insHotelWhere(dto);
    }
    public Integer insHotelPics(HotelInsPicDto dto) {
        return hotelMapper.insHotelPics(dto);
    }
    public Integer delHotelPic(HotelInsPicDto dto) {
        return hotelMapper.delHotelPic(dto);
    }
    public Integer insHotelOption(HotelInsDto dto) {
        return hotelMapper.insHotelOption(dto);
    }
    public Integer insHotelRoomInfo(InsHotelRoomDto dto) {
        return hotelMapper.insHotelRoomInfo(dto);
    }
    public Integer insHotelRoomInfoDate(InsHotelRoomDateInfoDto dto) {
        return hotelMapper.insHotelRoomInfoDate(dto);
    }
}
