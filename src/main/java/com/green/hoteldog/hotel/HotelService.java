package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import scala.collection.Seq;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

/*          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD"); //년월일 표시
            Calendar cal = Calendar.getInstance();
            cal.get(Calendar.YEAR);
            cal.set ( 2019, 1-1, 1 ); //종료 날짜 셋팅
            String endDate = dateFormat.format(cal.getTime());

            cal.set ( 2018, 1-1, 1 ); //시작 날짜 셋팅
            String startDate = dateFormat.format(cal.getTime());*/

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
            // 최종 출력
            for ( LocalDate dateList : dateRange ) {
                log.info("date : {}", dateList);
            }
            dto.setDate(dateRange);

            allVo.setHotelList(mapper.selHotelListToFilter(dto));
            log.info("allVo : {}", allVo);
            return allVo;
        }
        return null;
    }
}
