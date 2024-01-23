package com.green.hoteldog.hotel;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.user.models.UserHotelFavDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import scala.collection.Seq;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper mapper;

    // 호텔 광고 리스트 셀렉
    List<HotelListSelVo> getHotelAdvertiseList(HotelListSelDto dto){
        return mapper.selHotelAdvertiseList(dto);
    }

    HotelListSelAllVo getHotelList(HotelListSelDto dto){
        // 0. 랜덤 광고 리스트 셀렉
        List<HotelListSelVo> hotelAdvertiseList = mapper.selHotelAdvertiseList(dto);
        log.info("hotelAdvertiseList : {}", hotelAdvertiseList);
        HotelListSelAllVo allVo = new HotelListSelAllVo();
        allVo.setHotelAdvertiseList(hotelAdvertiseList);
        log.info("allVo : {}", allVo);

        // 1. 등록된 반려견 정보와 주소로 사용자화
        // 1-1. 비회원 첫화면 - 최신순
        if(dto.getUserPk() == 0 && dto.getAddress() == null && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getSearch() == null
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0
                && dto.getDogWalking() == 0){
            allVo.setHotelList(mapper.selHotelListToNonMember(dto));
            log.info("allVo : {}", allVo);
            return allVo;
            // 1-2. 회원 첫화면 - 주소, 반려견 강아지 사이즈
        } else if (dto.getUserPk() > 0 && dto.getAddress() != null && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getSearch() == null
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0
                && dto.getDogWalking() == 0){
            // 1-2-1. 회원 pk로 강아지 사이즈, 주소 pk 셀렉
            HotelListSelProcDto pDto = mapper.selUserInfoToUserPk(dto);
            // 1-2-1-1. 등록 된 강아지가 없을 때
            if(pDto.getDogSizePks().size() == 0){
                allVo.setHotelList(mapper.selHotelListAsUserAddress(pDto));
                log.info("allVo : {}", allVo);
                return allVo;
            } else {
                // 1-2-1-2. 등록 된 강아지가 있을 때
                allVo.setHotelList(mapper.selHotelListAsUserAddressAndDogInformation(pDto));
                log.info("allVo : {}", allVo);
                return allVo;
            }
            // 2. 호텔 이름 검색 시 리스트
        } else if (dto.getSearch() != null && dto.getAddress() == null && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getDogWalking() == 0
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0){
            // 2-1. 형태소 분석기 사용, String을 각각의 단어로 String 리스트를 만들어 줍니다.
            String text = dto.getSearch();
            CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
            Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
            List<String> tokensToStrList = OpenKoreanTextProcessorJava.tokensToJavaStringList(tokens);
            dto.setTokensToStrList(tokensToStrList);
            log.info("tokensToStrList : {}", tokensToStrList);
            int indexOfHotel = tokensToStrList.indexOf("호텔"); // 나중에 "애견"문구도 삭제 요망
            log.info("indexOfHotel : {}", indexOfHotel);
            tokensToStrList.remove(indexOfHotel);
            log.info("tokensToStrList : {}", tokensToStrList);

            allVo.setHotelList(mapper.selHotelListToSearch(dto));
            log.info("allVo : {}", allVo);

            return allVo;
            // 3. 필터링 시 리스트
        } else if ((dto.getAddress() != null || dto.getFromDate() != null || dto.getToDate() != null
                || dto.getDogCount() > 0 || dto.getSwimmingPool() > 0 || dto.getPlayGround() > 0
                || dto.getHandMadeFood() > 0 || dto.getPickUp() > 0 || dto.getDogBeauty() > 0
                || dto.getDogProgram() > 0 || dto.getDogWalking() > 0) && dto.getSearch() == null){
            allVo.setHotelList(mapper.selHotelListToFilter(dto));
            log.info("allVo : {}", allVo);
            return allVo;
        }
        return null;
    }
    //영웅




    public ResVo toggleHotelBookMark(UserHotelFavDto dto){
        int result=mapper.delHotelBookMark(dto);
        if(result==1){
            return new ResVo(2);
        }
        int result2= mapper.insHotelBookMark(dto);
        return new ResVo(result2);
    }
    //호텔 메인페이지
    public HotelMainPage getHotelDetail(HotelMainPageDto dto){
        //메인페이지 객체 생성
        HotelMainPage hotelMainPage=new HotelMainPage();
        // 호텔 기본적인 정보.
        HotelInfoVo hotelInfoVo=mapper.getHotelDetail(dto.getHotelPk());
        //호텔 사진 넣어줌.
        hotelInfoVo.setPics(mapper.getHotelPics(dto.getHotelPk()));
        List<String> option=mapper.hotelOptionInfo(dto.getHotelPk());
        hotelInfoVo.setHotelOption(option);

        //좋아요 많은 갯수대로 호텔에 적힌 리뷰 최대 3개까지 가져옴.
        List<HotelReviewVo> reviewThree=mapper.getHotelReviewThree(dto.getHotelPk());
        if(reviewThree.size()==0){
            // 리뷰 없음.
        }
        int countReview=mapper.isMoreHotelReview(dto.getHotelPk());
        if(countReview>3){
            hotelInfoVo.setIsMoreReview(1);//리뷰 더있니 => 0 to 1
        }
        hotelInfoVo.setReviewList(reviewThree);
        hotelMainPage.setHotelInfoVo(hotelInfoVo);
        //유저 있으면 유저 강아지 정보 삽입.
        if(dto.getUserPk()>=1){
            List<MyDog> myDogList=mapper.getMyDogs(dto.getUserPk());
            hotelMainPage.setMyDogList(myDogList);

            if(myDogList.size()==0){
                //유저는 있는데 등록된 강아지는 없음.
            }
        }
        List<LocalDate> twoMonthDate=getTwoMonth();
        //twoMonthDate : 두달동안 날짜 리스트(LocalDate 타입)

        //twoMonth : String 타입 리스트.
        List<String> twoMonth=twoMonthDate
                .stream()
                .map(localDate -> localDate.toString())
                .collect(Collectors.toList());

        HotelRoomAbleListDto listDto=null;

        listDto.setHotelPk(dto.getHotelPk());

        //메인페이지 첫화면은 호텔pk만 보내고 정리해서 줌.
        List<HotelRoomResInfoByMonth> hotelResInfoVos=getTwoMonthRoomAble(listDto);

        //박스갈이 & 데이터빼내기&검증
        List<HotelRoomEaByDate> eaByDates=new ArrayList<>();

        for (String date:twoMonth) {
            HotelRoomEaByDate eaByDate=new HotelRoomEaByDate();
            eaByDate.setDate(date);
            eaByDates.add(eaByDate);
        }
        //들어간 데이터 중 날짜정보만 빼옴.
        List<String> checkDate=eaByDates
                .stream()
                .map(HotelRoomEaByDate::getDate)
                .toList();
        if(!checkDate.containsAll(twoMonth)){
            //모든날짜 들어가지 않음. >> 에러.
        }

        //람다식?
        eaByDates.forEach(date -> {
            List<HotelRoomEa> roomEas = hotelResInfoVos
                    .stream()
                    .filter(resInfoByMonth -> date.getDate().equals(resInfoByMonth.getRoomDate()))
                    .map(resInfoByMonth -> {
                        HotelRoomEa hotelRoomEa = new HotelRoomEa();
                        hotelRoomEa.setHotelRoomNm(resInfoByMonth.getHotelRoomNm());
                        hotelRoomEa.setRoomLeftEa(resInfoByMonth.getRoomLeftEa());
                        return hotelRoomEa;
                    })
                    .collect(Collectors.toList());

            date.setRoomEas(roomEas);
        });


        hotelMainPage.setRoomEaByDates(eaByDates);

        return hotelMainPage;
    }


    //날짜 선택했을때 가능한 방 리스트
    public List<HotelRoomEaByDate> whenYouChooseDates(int hotelPk,String startDate,String endDate){
        List<HotelRoomResInfoByMonth> areYouChooseDates=getTwoMonthRoomAble(HotelRoomAbleListDto
                .builder()
                .hotelPk(hotelPk)
                .startDate(startDate)
                .endDate(endDate)
                .build());
        //날짜 맞는가 검증하는거 만들어아함.

        List<LocalDate> localDates=getTwoMonth();
        List<HotelRoomEaByDate> whenYouSelDates=new ArrayList<>();
        for (LocalDate date:localDates) {
            HotelRoomEaByDate eaByDate=new HotelRoomEaByDate();
            eaByDate.setDate(date.toString());
        }

        List<HotelRoomEaByDate> updatedList = new ArrayList<>();

        whenYouSelDates.forEach(date -> {
            List<HotelRoomEa> eaList = areYouChooseDates
                    .stream()
                    .filter(infoByMonth -> infoByMonth.getRoomDate().equals(date.toString()))
                    .map(infoByMonth -> {
                        HotelRoomEa roomEa = new HotelRoomEa();
                        roomEa.setHotelRoomNm(infoByMonth.getHotelRoomNm());
                        roomEa.setRoomLeftEa(infoByMonth.getRoomLeftEa());
                        return roomEa;
                    })
                    .collect(Collectors.toList());

            HotelRoomEaByDate eaByDate = new HotelRoomEaByDate();
            eaByDate.setDate(date.toString());
            eaByDate.setRoomEas(eaList);

            updatedList.add(eaByDate);
        });

        whenYouSelDates.addAll(updatedList);
        return whenYouSelDates;

    }
    //날짜랑 강아지 선택했을 때 가능한 방 리스트
    public List<HotelRoomEaByDate> whenYouChooseDatesAndDogs(int hotelPk,String startDate,String endDate,List<Integer> dogs){
        List<HotelRoomResInfoByMonth> areYouSure=getTwoMonthRoomAble(HotelRoomAbleListDto
                .builder()
                .hotelPk(hotelPk)
                .startDate(startDate)
                .endDate(endDate)
                .dogPks(dogs)
                .build());
        //얘도 가져온 날짜 맞는가 검증하는거 만들어야함.
        // 현재날짜기준 2달기준만 조회? 아니면 다른달도 조회가능?  => 얘기해봐야함

        List<LocalDate> localDates=getTwoMonth();
        List<HotelRoomEaByDate> whenYouChooseDatesAndDogs=new ArrayList<>();
        for (LocalDate date:localDates) {
            HotelRoomEaByDate eaByDate=new HotelRoomEaByDate();
            eaByDate.setDate(date.toString());
        }

        whenYouChooseDatesAndDogs.forEach(date -> {
            List<HotelRoomEa> eaList = areYouSure
                    .stream()
                    .filter(infoByMonth -> infoByMonth.getRoomDate().equals(date.toString()))
                    .map(infoByMonth -> {
                        HotelRoomEa roomEa = new HotelRoomEa();
                        roomEa.setHotelRoomNm(infoByMonth.getHotelRoomNm());
                        roomEa.setRoomLeftEa(infoByMonth.getRoomLeftEa());
                        return roomEa;
                    })
                    .collect(Collectors.toList());

            HotelRoomEaByDate eaByDate = new HotelRoomEaByDate();
            eaByDate.setDate(date.toString());
            eaByDate.setRoomEas(eaList);

            whenYouChooseDatesAndDogs.add(eaByDate);
        });

        return whenYouChooseDatesAndDogs;
    }

    //현재 날짜 기준으로 2달 세팅한거 삽입,hotelPk 삽입해서 방 받아오는거
    //시작날짜, 끝날짜 삽입, 호텔Pk 삽입 그날 가능한 날 방 리스트만 가져오기.
    //시작날짜, 끝날짜 삽입, 호텔Pk 삽입, 강아지들 사이즈 Pk 삽입, 방 리스트 가져오기.

    public List<HotelRoomResInfoByMonth> getTwoMonthRoomAble(HotelRoomAbleListDto dto){
        if(dto.getHotelPk()==0){
            //예외처리
        }
        List<LocalDate> twoMonthDate=getTwoMonth();
        //시작날짜 : 요번달 첫날
        String startDate=twoMonthDate.get(0).toString();
        //끝나는날짜 : 다음달 말일
        String endDate=twoMonthDate.get(twoMonthDate.size()).toString();

        // 호텔 상세페이지 들어갔을 때 (else 맨밑),날짜 선택했을때(중간),다 선택했을때(바로밑)
        if(!(dto.getStartDate()==null&&dto.getEndDate()==null&&dto.getDogPks().size()==0)){
            //시작 끝 날짜 입력 되었고 개새끼 정보도 입력된 상태.(예약 마지막단계)
            dto.getDogPks();
            int howMany=dto.getDogPks().size();
            int large=dto.getDogPks()
                    .stream()
                    .mapToInt(v -> v)
                    .max()
                    .orElse(0);
            return mapper.getHotelFilterRoomResInfo(dto.getHotelPk(),dto.getStartDate(),dto.getEndDate(),howMany,large);

        }else if(!(dto.getEndDate()==null&&dto.getStartDate()==null)){
            //시작 끝 날짜만 입력 되어있는 상태.(예약 2단계)

            return mapper.getHotelRoomResInfo(dto.getHotelPk(),dto.getStartDate(), dto.getEndDate());

        }else{
            // 시작 , 끝 날짜 설정X, (예약 1단계 or 상세페이지)
            return mapper.getHotelRoomResInfo(dto.getHotelPk(),startDate,endDate);
        }
    }
    public List<LocalDate> getTwoMonth(){
        LocalDate today=LocalDate.now();
        List<LocalDate> twoMonthDate=new ArrayList<>();
        //요번달 날짜,
        for (int i = 1; i < today.lengthOfMonth() ; i++) {
            LocalDate localDate=LocalDate.now().plusDays(i- today.getDayOfMonth());
            twoMonthDate.add(localDate);
        }
        //다음달 날짜,
        for (int i = 0; i <= today.plusMonths(1).lengthOfMonth(); i++) {
            LocalDate localDate=LocalDate.now().plusMonths(1).plusDays(i- today.getDayOfMonth());
            twoMonthDate.add(localDate);
        }
        return twoMonthDate;
    }
    //혹시나 몰라서....
    public List<LocalDate> getMonthIChoose(int year, int month){

        List<LocalDate> monthDateList=new ArrayList<>();
        //요번달 날짜,

        LocalDate today=LocalDate.of(year,month,1);
        for (int i = 1; i < today.lengthOfMonth() ; i++) {
            LocalDate localDate=LocalDate.now().plusDays(i- today.getDayOfMonth());
            monthDateList.add(localDate);
        }
        return monthDateList;
    }
    //승준
}
