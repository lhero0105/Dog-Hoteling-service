package com.green.hoteldog.review;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.review.model.ReveiwDelDto;
import com.green.hoteldog.review.model.ReviewFavDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReveiwController {
    private final ReveiwService service;

    @DeleteMapping
    public  ResVo delUserReveiw(ReveiwDelDto dto){
        return service.delUserReveiw(dto);
    }

    @GetMapping("/fav")
    public ResVo toggleReviewFav(ReviewFavDto dto){
        return service.toggleReviewFav(dto);
    }
}
