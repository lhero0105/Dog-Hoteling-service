package com.green.hoteldog.review;

import com.green.hoteldog.review.model.HotelReviewPicsSelVo;
import com.green.hoteldog.review.model.HotelReviewSelDto;
import com.green.hoteldog.review.model.HotelReviewSelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReveiwService {
    private final ReviewMapper mapper;

    public List<HotelReviewSelVo> getHotelReview(HotelReviewSelDto dto){
        // n+1 이슈 해결
        List<HotelReviewSelVo> list = mapper.selHotelReview(dto);
        for ( HotelReviewSelVo vo : list ) {
            dto.getReviewPk().add(vo.getReviewPk());
        }
        List<HotelReviewPicsSelVo> pics = mapper.selHotelReviewPics(dto);

        // pk를 담을 list, pk 및 해당 객체 주솟 값을 담을 map 생성
        List<Integer> revPk = new ArrayList<>();
        Map<Integer, HotelReviewSelVo> hashMap = new HashMap<>();
        for ( HotelReviewSelVo vo : list ) {
            revPk.add(vo.getReviewPk());
            hashMap.put(vo.getReviewPk(), vo);
        }
        for ( HotelReviewPicsSelVo vo : pics ) {
            hashMap.get(vo.getReviewPk()).getPics().add(vo.getPic());
        }
        return list;
    }
}
