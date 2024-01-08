package com.green.hoteldog.review;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.review.model.ReveiwDelDto;
import com.green.hoteldog.review.model.ReviewFavDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReveiwService {
    private final ReviewMapper mapper;
    private final ReviewFavMapper ReviewFavMapper;

    // 리뷰 삭제
    public  ResVo delUserReveiw(ReveiwDelDto dto){
        int del = mapper.delUserReveiw(dto);
        return new ResVo(Const.SUCCESS);
    }

// 리뷰 좋아요 토굴 삭제 인설트 딸깍딸깎
    public ResVo toggleReviewFav(ReviewFavDto dto){
        int delRows = ReviewFavMapper.delReviewFav(dto);
        if (delRows==1){
            return new ResVo(Const.FEED_FAV_DEL);
        }
        int insRows = ReviewFavMapper.insReviewFav(dto);
        return new ResVo(Const.FEED_FAV_ADD);
    }



}
