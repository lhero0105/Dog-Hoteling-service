package com.green.hoteldog.review;

import com.green.hoteldog.review.model.HotelReviewSelDto;
import com.green.hoteldog.review.model.HotelReviewSelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/res")
public class ReveiwController {
    private final ReveiwService service;

    @GetMapping("/{hotel_pk}/review/{page}")
    public List<HotelReviewSelVo> getHotelReview(@RequestParam ("hotel_pk") int hotelPk, @RequestParam int page){
        HotelReviewSelDto dto = new HotelReviewSelDto();
        dto.setRowCount(3);
        dto.setHotelPk(hotelPk);
        dto.setPage(page);
        return service.getHotelReview(dto);
    }
}
