package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.HotelListSelAllVo;
import com.green.hoteldog.hotel.model.HotelListSelDto;
import com.green.hoteldog.hotel.model.HotelListSelProcDto;
import com.green.hoteldog.hotel.model.HotelListSelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import scala.collection.Seq;

import java.util.List;

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

}
