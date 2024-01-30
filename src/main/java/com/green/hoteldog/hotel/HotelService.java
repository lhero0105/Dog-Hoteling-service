package com.green.hoteldog.hotel;


import com.green.hoteldog.common.AppProperties;
import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.MyFileUtils;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.exceptions.CommonErrorCode;
import com.green.hoteldog.exceptions.CustomException;
import com.green.hoteldog.exceptions.HotelErrorCode;
import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.security.AuthenticationFacade;
import com.green.hoteldog.user.models.UserHotelFavDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import scala.collection.Seq;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper mapper;
    private final AppProperties appProperties;
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;


    //-----------------------------------------------호텔 광고 리스트 셀렉트------------------------------------------------
    public List<HotelListSelVo> getHotelAdvertiseList(HotelListSelDto dto){
        return mapper.selHotelAdvertiseList(dto);
    }
    //-----------------------------------------------호텔 리스트 셀렉트----------------------------------------------------
    public HotelListSelAllVo getHotelList(HotelListSelDto dto){
        // 0. 랜덤 광고 리스트 셀렉
        List<HotelListSelVo> hotelAdvertiseList = mapper.selHotelAdvertiseList(dto);
        log.info("hotelAdvertiseList : {}", hotelAdvertiseList);
        HotelListSelAllVo allVo = new HotelListSelAllVo();
        allVo.setHotelAdvertiseList(hotelAdvertiseList);
        log.info("allVo : {}", allVo);
        // option pk size 값 넣기
        // dog pk size 값 넣기
        if (dto.getHotelOptionPk().size() != 0){
            dto.setOptionPkSize(dto.getHotelOptionPk().size());
        } else if(dto.getDogPkSize() != 0){
            List<Integer> blankList =dto.getDogInfo()
                    .stream()
                    .map(DogSizeEa::getDogSize)
                    .collect(Collectors.toList());

            dto.setDogPkSize(blankList.size());
        }
        // 1. 등록된 반려견 정보와 주소로 사용자화
        // 1-1. 비회원 첫화면 - 최신순
        if(dto.getUserPk() == 0 && dto.getAddress() == null && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogInfo().size() == 0 && dto.getSearch() == null
                && dto.getHotelOptionPk().size() == 0){
            allVo.setHotelList(mapper.selHotelListToNonMember(dto));
            log.info("allVo : {}", allVo);
            return allVo;
            // 1-2. 회원 첫화면 - 주소, 반려견 강아지 사이즈
        } else if (dto.getUserPk() > 0 && dto.getAddress() != null && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogInfo().size() == 0 && dto.getSearch() == null
                && dto.getHotelOptionPk().size() == 0){
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
                && dto.getToDate() == null && dto.getDogInfo().size() == 0 && dto.getHotelOptionPk().size() == 0){
            // 2-1. 정확하게 입력된 호텔이름을 먼저 셀렉트
            List<HotelListSelVo> accurateHotelList = mapper.selHotelListToAccurateSearch(dto);
            log.info("accurateHotelList.size() : {}", accurateHotelList.size());

            // 2-2. 형태소 분석기 사용, String을 각각의 단어로 String 리스트를 만들어 줍니다.
            String text = dto.getSearch();
            CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
            Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
            List<String> tokensToStrList = OpenKoreanTextProcessorJava.tokensToJavaStringList(tokens);
            dto.setTokensToStrList(tokensToStrList);
            log.info("tokensToStrList : {}", tokensToStrList);
            // 나중에 에러 뜨면 if문 작성
            int index1 = tokensToStrList.indexOf("호텔");
            int index2 = tokensToStrList.indexOf("애견");
            log.info("index1 : {}", index1);
            tokensToStrList.remove(index1);
            log.info("index2 : {}", index2);
            tokensToStrList.remove(index2);
            log.info("tokensToStrList : {}", tokensToStrList);

            List<HotelListSelVo> HotelList = mapper.selHotelListToSearch(dto);
            // page가 1일 때에만 정확한 검색어를 첫번째로 저장해서 보냅니다.
            // 정확한 검색어 수만큼 따라 마지막 인덱스 제거
            if(accurateHotelList.size() > 0 && dto.getPage() == 1){
                accurateHotelList.addAll(HotelList);
                allVo.setHotelList(accurateHotelList);
                log.info("allVo : {}", allVo);
                for (int i = 0; i < accurateHotelList.size(); i++) {
                    allVo.getHotelList().remove(allVo.getHotelList().size() - 1);
                }
                return allVo;
            }else {
                allVo.setHotelList(HotelList);
                log.info("allVo : {}", allVo);
                return allVo;
            }
            // 나중에 시간날 때 정확한 순으로 정렬하는 코드 작성
            // String[] filteredArray = filterAndSortStrings(vo.getHotelNm(), "a", "e");

            /*
            예시
            "부평싸이호텔" 검색 시
	        -> 부평싸이호텔 1개 셀렉트
	        -> 유사한 검색어도 셀렉
            "부평싸일호텔" 검색 시
	        -> 유사한 검색어 셀렉
            */

            // 3. 필터링 시 리스트
        } else if ((dto.getAddress() != null || dto.getFromDate() != null || dto.getToDate() != null
                || dto.getDogInfo().size() > 0) && dto.getSearch() == null || dto.getHotelOptionPk().size() > 0){
            //  LocalDate, Calendar 방법 두 가지

            // "yyyy-MM-dd" 포맷을 사용하여 문자열을 LocalDate로 파싱
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fromDate = LocalDate.parse(dto.getFromDate(), formatter);
            LocalDate toDate = LocalDate.parse(dto.getFromDate(), formatter);

            // 년, 월, 일 추출
            int fromYear = fromDate.getYear();
            int fromMonth = fromDate.getMonthValue();
            int fromDay = fromDate.getDayOfMonth();

            int toYear = toDate.getYear();
            int toMonth = toDate.getMonthValue();
            int toDay = toDate.getDayOfMonth();

            LocalDate fromDateUnit = LocalDate.of(fromYear, fromMonth, fromDay);
            LocalDate toDateUnit = LocalDate.of(toYear, toMonth, toDay);

            List<LocalDate> dateRange = new ArrayList<>();

            // fromDate부터 toDate까지 날짜 배열 생성
            while (!fromDateUnit.isAfter(toDateUnit)) {
                dateRange.add(fromDateUnit);
                fromDateUnit = fromDateUnit.plusDays(1);
            }
            // 날짜 출력
            for ( LocalDate dateList : dateRange ) {
                log.info("date : {}", dateList);
            }
            dto.setDate(dateRange);

            // 개별 방에 관한 호텔 pk 셀렉
            List<Integer> list = new ArrayList<>();
            DogSizeInfoIn dogSizeInfo = new DogSizeInfoIn();
            dogSizeInfo.setDate(dateRange);
            for ( DogSizeEa ea : dto.getDogInfo() ) {
                dogSizeInfo.setDogSize(ea.getDogSize());
                dogSizeInfo.setDogCount(ea.getDogCount());
                List<Integer> hotelPk1 = mapper.selHotelPkToIndividualDogInfo(dogSizeInfo);
                list.addAll(hotelPk1);
            }
            // 단체 방에 관한 호텔 pk 셀렉
            DogSizeInfoGr dogSizeinfoGr = new DogSizeInfoGr();
            dogSizeinfoGr.setDate(dateRange);
            List<Integer> dogSize = new ArrayList<>();
            int allCount = 0;
            for ( DogSizeEa ea : dto.getDogInfo() ) {
                allCount =+ ea.getDogCount();
                dogSize.add(ea.getDogSize());
            }
            dogSizeinfoGr.setAllDogCount(allCount);
            Integer maxValue = dogSize.stream()
                    .mapToInt(x -> x)
                    .max()
                    .orElse(0); // 예외 시
            log.info("maxValue : {}", maxValue);
            dogSizeinfoGr.setBiggestDogSize(maxValue);
            List<Integer> hotelPk2 = mapper.selHotelPkToGroupDogInfo(dogSizeinfoGr);
            list.addAll(hotelPk2);
            // 단체 방 + 개별 방 종합 호텔 pk 셀렉 구현


            // 중복 제거
            List<Integer> filteredPk = list.stream().distinct().collect(Collectors.toList());
            log.info("filteredPk : {}", filteredPk);
            dto.setFilteredPk(filteredPk);
            allVo.setHotelList(mapper.selHotelListToFilter(dto));

            log.info("allVo : {}", allVo);
            return allVo;
        }
        return null;
    }
    // 영웅



    //----------------------------------------------호텔 북마크-----------------------------------------------------------
    public ResVo toggleHotelBookMark(int hotelPk,int userPk){
        int result=mapper.delHotelBookMark(userPk,hotelPk);
        if(result==1){
            return new ResVo(3);
        }
        int result2= mapper.insHotelBookMark(userPk,hotelPk);
        return new ResVo(result2);
    }

    public List<HotelBookMarkListVo> getHotelBookmarkList(int userPk,int page){
        int fromPage=(page-1)*6;
        int toPage=page*6;
        List<HotelBookMarkListVo> getBookMarkList=mapper.getHotelBookMark(userPk,fromPage,toPage);
        return getBookMarkList;

    }
    //--------------------------------------------호텔 상세페이지----------------------------------------------------------
    public HotelInfoEntity getHotelDetail(int hotelPk){
        if(hotelPk>0) {
            int userPk= authenticationFacade.getLoginUserPk();
            log.info("userPk : {}",userPk);

            //메인페이지 객체 생성
            HotelInfoEntity hotelInfoEntity = new HotelInfoEntity();
            // 호텔 기본적인 정보.
            HotelInfoVo hotelInfoVo = mapper.getHotelDetail(hotelPk);
            //호텔 사진 넣어줌.
            hotelInfoVo.setPics(mapper.getHotelPics(hotelPk));
            List<String> option = mapper.hotelOptionInfo(hotelPk);
            hotelInfoVo.setHotelOption(option);

            //좋아요 많은 갯수대로 호텔에 적힌 리뷰 최대 3개까지 가져옴.
            List<HotelReviewVo> reviewThree = mapper.getHotelReviewThree(hotelPk);
            int countReview = mapper.isMoreHotelReview(hotelPk);
            if (countReview > 3) {
                hotelInfoVo.setIsMoreReview(1);//리뷰 더있니 => 0 to 1
                log.info("isMoreReview : {}", hotelInfoVo.getIsMoreReview());
            }
            if(reviewThree.size()>0){
                hotelInfoVo.setReviewList(reviewThree);
            }
            List<HotelRoomInfoVo> roomInfoVos=mapper.getHotelRoomInfo(hotelPk);
            hotelInfoVo.setRoomList(roomInfoVos);


            hotelInfoEntity.setHotelInfoVo(hotelInfoVo);

            List<LocalDate> twoMonthDate = getTwoMonth();
            //twoMonthDate : 두달동안 날짜 리스트(LocalDate 타입)

            //twoMonth : String 타입 리스트.
            List<String> twoMonth = twoMonthDate
                    .stream()
                    .map(localDate -> localDate.toString())
                    .collect(Collectors.toList());

            HotelRoomAbleListDto ableListDto = new HotelRoomAbleListDto();
            ableListDto.setHotelPk(hotelPk);
            //메인페이지 첫화면은 호텔pk만 보내고 정리해서 줌.
            List<HotelRoomResInfoByMonth> hotelResInfoVos = iWillShowYouAbleDatesWithRoom(ableListDto);
            if(hotelResInfoVos.size()==0){

            }
            //박스갈이 & 데이터빼내기&검증
            List<HotelRoomEaByDate> eaByDates = new ArrayList<>();

            for (String date : twoMonth) {
                HotelRoomEaByDate eaByDate = new HotelRoomEaByDate();
                eaByDate.setDate(date);
                eaByDates.add(eaByDate);
                log.info("date : {}", date);
            }
            //들어간 데이터 중 날짜정보만 빼옴.
            List<String> checkDate = eaByDates
                    .stream()
                    .map(HotelRoomEaByDate::getDate)
                    .toList();
            if (!checkDate.containsAll(twoMonth)) {
                throw new CustomException(CommonErrorCode.CONFLICT);
            }
            //근데 이 위에것 굳이 할 필요가 있을까??
            //람다식?
            //forEach반복문 .
            eaByDates.forEach(date -> {     //eaByDates 의 String 하나를 date 으로 지정.
                List<HotelRoomEa> roomEas = hotelResInfoVos     //RoomEaByDate 안의 객체인 List<HotelRoomEa> 생성 후 스트림.
                        .stream()
                        .filter(getDate -> date.getDate().equals(getDate.getRoomDate()))//날짜와 방의 날짜가 같은것 필터.
                        .map(roomInfo -> {//맵 생성
                            HotelRoomEa hotelRoomEa = new HotelRoomEa();
                            hotelRoomEa.setHotelRoomNm(roomInfo.getHotelRoomNm());
                            hotelRoomEa.setRoomLeftEa(roomInfo.getRoomLeftEa()); //넣어줌
                            return hotelRoomEa;//반환
                        })
                        .collect(Collectors.toList());//리스트로 반환

                date.setRoomEas(roomEas); //하나하나 넣어줌.
                log.info("roomEas : {}", roomEas);
            });

            hotelInfoEntity.setRoomEaByDates(eaByDates);

            if (userPk >= 1) {
                List<MyDog> myDogList = mapper.getMyDogs(userPk);
                if(myDogList.size()>0){
                    hotelInfoEntity.setMyDogList(myDogList);
                }
                log.info("myDogList : {}", hotelInfoEntity.getMyDogList());
            }
            return hotelInfoEntity;
        }
        throw new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }


    //----------------------------------------날짜 선택했을때 가능한 방 리스트------------------------------------------------
    public List<HotelRoomEaByDate> whenYouChooseDates(int hotelPk,LocalDate startDate,LocalDate endDate){
        if(hotelPk>0) {
            if (hotelPk == 0 || startDate == null || endDate == null) {
                throw new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND);
            }
            HotelRoomAbleListDto ableListDto = new HotelRoomAbleListDto();
            ableListDto.setHotelPk(hotelPk);
            ableListDto.setEndDate(endDate.toString());
            ableListDto.setStartDate(startDate.toString());
            List<HotelRoomResInfoByMonth> areYouChooseDates = iWillShowYouAbleDatesWithRoom(ableListDto);

            List<HotelRoomEaByDate> whenYouSelDates = new ArrayList<>();
            List<LocalDate> getList = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
            List<String> getDateList = getList
                    .stream()
                    .map(date -> date.toString())
                    .collect(Collectors.toList());

            getList.forEach(System.out::println);
            List<HotelRoomEaByDate> updatedList = new ArrayList<>();

            getDateList.forEach(date -> {
                List<HotelRoomEa> eaList = areYouChooseDates
                        .stream()
                        .filter(s -> s.getRoomDate().equals(date.toString()))
                        .map(roomInfo -> {
                            HotelRoomEa roomEa = new HotelRoomEa();
                            roomEa.setHotelRoomNm(roomInfo.getHotelRoomNm());
                            roomEa.setRoomLeftEa(roomInfo.getRoomLeftEa());
                            return roomEa;
                        })
                        .collect(Collectors.toList());

                HotelRoomEaByDate eaByDate = new HotelRoomEaByDate();
                eaByDate.setDate(date.toString());
                eaByDate.setRoomEas(eaList);
                log.info("eaList : {}", eaList);
                updatedList.add(eaByDate);
            });

            whenYouSelDates.addAll(updatedList);
            return whenYouSelDates;
        }
        throw new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }
    //------------------------------------날짜랑 강아지 선택했을 때 가능한 방 리스트--------------------------------------------
    public List<HotelRoomEaByDate> whenYouChooseDatesAndDogs(int hotelPk,LocalDate startDate,LocalDate endDate,List<Integer> dogs){
        if(hotelPk==0||startDate==null||endDate==null||dogs.size()==0){
            throw new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        if(dogs.size()>0) {
            int howMany = dogs.size();
            int large = dogs
                    .stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElse(0);
            log.info("hotelPk : {}", hotelPk);
            log.info("startDate : {}", startDate);
            log.info("endDate : {}", endDate);
            log.info("dogs : {}", dogs);

            List<LocalDate> getList=startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
            List<String> getListDates=getList
                    .stream()
                    .map(date -> date.toString())
                    .collect(Collectors.toList());

            getList.forEach(System.out::println);

            List<HotelRoomEaByDate> whenYouChooseDatesAndDogs=new ArrayList<>();
            List<HotelRoomResInfoByMonth> areYouSure =mapper.getHotelFilterRoomResInfo(hotelPk,startDate.toString(),endDate.toString(),howMany,large);
            
            getListDates.forEach(date -> {
                List<HotelRoomEa> eaList = areYouSure
                        .stream()
                        .filter(s -> s.getRoomDate().equals(date.toString()))
                        .map(roomInfo -> {
                            HotelRoomEa roomEa = new HotelRoomEa();
                            roomEa.setHotelRoomNm(roomInfo.getHotelRoomNm());
                            roomEa.setRoomLeftEa(roomInfo.getRoomLeftEa());
                            return roomEa;
                        })
                        .collect(Collectors.toList());

                HotelRoomEaByDate eaByDate = new HotelRoomEaByDate();
                eaByDate.setDate(date.toString());
                eaByDate.setRoomEas(eaList);

                whenYouChooseDatesAndDogs.add(eaByDate);
                log.info("eaByDates : {}", eaByDate);
            });
            return whenYouChooseDatesAndDogs;
        }
        throw new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }
    //------------------------------------------가능한 방 리스트 필터링-----------------------------------------------------

    public List<HotelRoomResInfoByMonth> iWillShowYouAbleDatesWithRoom(HotelRoomAbleListDto dto) {
        if (dto.getHotelPk() == 0) {
            throw new CustomException(HotelErrorCode.NON_EXIST_HOTEL_PK);
        }
        //시작 끝 날짜만 입력 되어있는 상태.(예약 2단계)
        if (!(dto.getEndDate() == null && dto.getStartDate() == null)) {
            log.info("hotelPk : {}", dto.getHotelPk());
            log.info("startDate : {}", dto.getStartDate());
            log.info("endDate : {}", dto.getEndDate());
            List<HotelRoomResInfoByMonth> resInfoByMonths=mapper.getHotelRoomResInfo(dto.getHotelPk(), dto.getStartDate(), dto.getEndDate());
            if(resInfoByMonths.size()==0){
                throw new CustomException(HotelErrorCode.NON_EXIST_ROOM_DATE);
            }
            return resInfoByMonths;
            // 시작 , 끝 날짜 설정X, (예약 1단계 or 상세페이지)
        } else {
            List<LocalDate> twoMonthDate = getTwoMonth();
            String startDate = twoMonthDate.get(0).toString();//시작날짜 : 요번달 첫날
            String endDate = twoMonthDate.get(twoMonthDate.size() -1).toString();//끝나는날짜 : 다음달 말일
            log.info("hotelPk : {}", dto.getHotelPk());

            List<HotelRoomResInfoByMonth> infoByMonths=mapper.getHotelRoomResInfo(dto.getHotelPk(), startDate, endDate);
            if(infoByMonths.size()==0){
                throw new CustomException(HotelErrorCode.NON_EXIST_ROOM_DATE);
            }
            return infoByMonths;
        }
    }
    //---------------------------------------------2달 생성--------------------------------------------------------------
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

        LocalDate today=LocalDate.of(year,month,1);
        for (int i = 1; i < today.lengthOfMonth() ; i++) {
            LocalDate localDate=LocalDate.now().plusDays(i- today.getDayOfMonth());
            monthDateList.add(localDate);
        }
        return monthDateList;
    }
    //승준

    // 호텔 더미데이터 작성
    @Transactional(rollbackFor = Exception.class)
    public ResVo hotelRegistration(List<MultipartFile> pics, HotelInsDto dto){
        dto.setUserPk(authenticationFacade.getLoginUserPk());
        if(dto.getUserPk() == 0){
            //예외처리
            return new ResVo(0);
        }
        mapper.insHotel(dto);
        if(pics != null){
            List<String> hotelPics = new ArrayList<>();
            String target = "/board/"+dto.getHotelPk();
            for(MultipartFile file : pics){
                String saveFileNm = myFileUtils.transferTo(file,target);
                hotelPics.add(saveFileNm);
            }
            dto.setPics(hotelPics);
            try {
                mapper.insHotelPics(dto);
                return new ResVo(1);
            }catch (Exception e){
                return new ResVo(0);
            }
        }
        dto.getAddressDto().setHotelPk(dto.getHotelPk());
        mapper.insHotelOption(dto);
        mapper.insHotelWhere(dto.getAddressDto());
        return new ResVo(1);
    }
    @Transactional(rollbackFor = Exception.class)
    public ResVo insHotelRoom(MultipartFile hotelPic ,InsHotelRoomDto dto){
        dto.setUserPk(authenticationFacade.getLoginUserPk());
        if(dto.getUserPk() == 0){
            return new ResVo(0);
        }
        if(hotelPic != null){
            String target = "/board/"+dto.getHotelPk() + "/" + dto.getHotelRoomNm();
            String saveFileNm = myFileUtils.transferTo(hotelPic,target);
            dto.setRoomPic(saveFileNm);
        }
        mapper.insHotelRoomInfo(dto);
        List<LocalDate> towMonth = getTwoMonth();
        InsHotelRoomDateInfoDto dateInfoDto = new InsHotelRoomDateInfoDto();
        dateInfoDto.setHotelRoomPk(dto.getHotelRoomPk());
        dateInfoDto.setHotelLeftEa(dto.getHotelRoomEa());
        dateInfoDto.setRoomDate(towMonth);
        mapper.insHotelRoomInfoDate(dateInfoDto);
        return new ResVo(1);

    }
}
