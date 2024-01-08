package com.green.hoteldog.review;

import com.green.hoteldog.review.model.ReviewFavDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface ReviewFavMapper {
    int insReviewFav(ReviewFavDto dto);
    int delReviewFav(ReviewFavDto dto);
}
