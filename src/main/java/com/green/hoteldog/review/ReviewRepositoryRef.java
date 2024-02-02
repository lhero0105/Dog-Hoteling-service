package com.green.hoteldog.review;

import com.green.hoteldog.review.models.*;

import java.util.List;

public interface ReviewRepositoryRef {
    Integer checkResStatus(ReviewInsDto dto);
    Integer insReview(ReviewInsDto dto);
    Integer insReviewPics(ReviewInsPicsDto dto);
    Integer updReview(ReviewUpdDto dto);
    Integer delReviewPics(ReviewUpdDto dto);
    Integer updReviewComment(ReviewPatchDto dto);
    Integer delReviewFav(ReviewFavDto dto);
    Integer insReviewFav(ReviewFavDto dto);
    List<HotelReviewSelVo> selHotelReviewList(HotelReviewSelDto dto);
    List<ReviewPicVo> selReviewPics(List<Integer> reviewPkList);
    Integer delReview(DelReviewDto dto);
    List<HotelReviewSelVo> selHotelReview(HotelReviewSelDto dto);
    List<HotelReviewPicsSelVo> selHotelReviewPics(HotelReviewSelDto dto);
    Integer checkResUser(CheckResUserDto dto);
    Integer delReviewFavAll(DelReviewDto dto);
    Integer delReviewPicsAll(DelReviewDto dto);
}
