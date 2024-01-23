package com.green.hoteldog.review;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.review.models.*;
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
public class ReveiwController {
    private final ReveiwService service;

    //----------------------------------------------------리뷰등록--------------------------------------------------------
    @PostMapping
    public ResVo postReview (@RequestPart(required = false) List<MultipartFile> pics,
                             @RequestBody @Valid ReviewInsDto dto){

        dto.setPics(pics);
        return service.insReview(dto);
    }

    //-------------------------------------------------리뷰 전체 수정------------------------------------------------------
    @PutMapping
    public ResVo putReview (@RequestPart(required = false) List<MultipartFile> pics,
                            @RequestBody @Valid ReviewUpdDto dto){
        dto.setPics(pics);
        return service.putReview(dto);
    }

    //------------------------------------------------리뷰 코멘트 수정-----------------------------------------------------
    @PatchMapping
    public ResVo patchReviewComment (@RequestBody @Valid ReviewPatchDto dto){
        return service.patchReviewComment(dto);
    }
    //--------------------------------------------------리뷰 좋아요-------------------------------------------------------
    @GetMapping("/fav")
    public ResVo getReviewFav (int reviewPk){
        return service.patchReviewFav(reviewPk);
    }

    //-------------------------------------------상세페이지 리뷰 페이지네이션-------------------------------------------------
    @GetMapping("/{hotel_pk}/review/{page}")
    public List<HotelReviewSelVo> getHotelReview(@RequestParam ("hotel_pk") int hotelPk, @RequestParam int page){
        HotelReviewSelDto dto = new HotelReviewSelDto();
        dto.setRowCount(4);
        dto.setHotelPk(hotelPk);
        dto.setPage(page);
        return service.getHotelReview(dto);
    }
    //영웅

}
