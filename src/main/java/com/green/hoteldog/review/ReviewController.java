package com.green.hoteldog.review;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.review.models.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService service;

    //----------------------------------------------------리뷰등록--------------------------------------------------------
    @PostMapping
    @Operation(summary = "리뷰 등록", description = "리뷰 등록<br>사진 등록은 postman으로 테스트")
    public ResVo postReview(@RequestPart(required = false) List<MultipartFile> pics,
                            @RequestBody @Valid ReviewInsDto dto) {
        if (pics.size() > 3) {
            return new ResVo(0);
        }
        dto.setPics(pics);
        return service.insReview(dto);
    }

    //-------------------------------------------------리뷰 전체 수정------------------------------------------------------
    @PutMapping
    @Operation(summary = "리뷰 수정", description = "리뷰 수정<br>사진 등록은 postman으로 테스트")
    public ResVo putReview(@RequestPart(required = false) List<MultipartFile> pics,
                           @RequestBody @Valid ReviewUpdDto dto) {
        if (pics.size() > 3) {
            return new ResVo(0);
        }
        dto.setPics(pics);
        return service.putReview(dto);
    }

    //------------------------------------------------리뷰 코멘트 수정-----------------------------------------------------
    @PatchMapping
    @Operation(summary = "리뷰 코멘트 수정", description = "리뷰 코멘트만 수정")
    public ResVo patchReviewComment(@RequestBody @Valid ReviewPatchDto dto) {
        return service.patchReviewComment(dto);
    }

    //--------------------------------------------------리뷰 좋아요-------------------------------------------------------
    @GetMapping("/fav")
    @Operation(summary = "리뷰 좋아요", description = "좋아요 토글 처리<br>result = 1 좋아요 성공<br>result = 2 좋아요 취소")
    public ResVo getReviewFav(int reviewPk) {
        return service.patchReviewFav(reviewPk);
    }

    //-------------------------------------------상세페이지 리뷰 페이지네이션-------------------------------------------------
    @GetMapping("/{hotel_pk}/review/{page}")
    public List<HotelReviewSelVo> getHotelReview(@RequestParam("hotel_pk") int hotelPk, @RequestParam int page) {
        HotelReviewSelDto dto = new HotelReviewSelDto();
        dto.setRowCount(3);
        dto.setHotelPk(hotelPk);
        dto.setPage(page);
        return service.getHotelReview(dto);
    }
    //영웅

}
