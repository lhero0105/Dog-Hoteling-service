package com.green.hoteldog.review;

import com.green.hoteldog.review.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ReviewRepository implements ReviewRepositoryRef{
    private final ReviewMapper reviewMapper;
    public Integer checkResStatus(ReviewInsDto dto) {
        return reviewMapper.checkResStatus(dto);
    }
    public Integer insReview(ReviewInsDto dto) {
        return reviewMapper.insReview(dto);
    }
    public Integer insReviewPics(ReviewInsPicsDto dto) {
        return reviewMapper.insReviewPics(dto);
    }
    public Integer updReview(ReviewUpdDto dto) {
        return reviewMapper.updReview(dto);
    }
    public Integer delReviewPics(ReviewUpdDto dto) {
        return reviewMapper.delReviewPics(dto);
    }
    public Integer updReviewComment(ReviewPatchDto dto) {
        return reviewMapper.updReviewComment(dto);
    }
    public Integer delReviewFav(ReviewFavDto dto) {
        return reviewMapper.delReviewFav(dto);
    }
    public Integer insReviewFav(ReviewFavDto dto) {
        return reviewMapper.insReviewFav(dto);
    }
    public List<HotelReviewSelVo> selHotelReviewList(HotelReviewSelDto dto) {
        return reviewMapper.selHotelReviewList(dto);
    }
    public List<ReviewPicVo> selReviewPics(List<Integer> reviewPkList) {
        return reviewMapper.selReviewPics(reviewPkList);
    }
    public Integer delReview(DelReviewDto dto) {
        return reviewMapper.delReview(dto);
    }
    public List<HotelReviewSelVo> selHotelReview(HotelReviewSelDto dto) {
        return reviewMapper.selHotelReview(dto);
    }
    public List<HotelReviewPicsSelVo> selHotelReviewPics(HotelReviewSelDto dto) {
        return reviewMapper.selHotelReviewPics(dto);
    }
    public Integer checkResUser(CheckResUserDto dto) {
        return reviewMapper.checkResUser(dto);
    }
    public Integer delReviewFavAll(DelReviewDto dto) {
        return reviewMapper.delReviewFavAll(dto);
    }
    public Integer delReviewPicsAll(DelReviewDto dto) {
        return reviewMapper.delReviewFavAll(dto);
    }
}
