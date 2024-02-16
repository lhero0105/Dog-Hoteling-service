package com.green.hoteldog.review;

import com.green.hoteldog.review.models.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
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
    List<UserReviewVo> selUserResPk(int userPk);
    List<UserResRoomVo> selUserResRoomInfo(List<Integer> resPkList);
    List<UserReviewPic>selUserReviewPics(List<Integer> reviewPkList);
}
