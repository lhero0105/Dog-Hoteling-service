package com.green.hoteldog.review;

import com.green.hoteldog.review.model.HotelReviewPicsSelVo;
import com.green.hoteldog.review.model.HotelReviewSelDto;
import com.green.hoteldog.review.model.HotelReviewSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<HotelReviewSelVo> selHotelReview(HotelReviewSelDto dto);
    List<HotelReviewPicsSelVo> selHotelReviewPics(HotelReviewSelDto dto);
}