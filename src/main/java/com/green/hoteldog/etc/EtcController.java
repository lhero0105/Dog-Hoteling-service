package com.green.hoteldog.etc;

import com.green.hoteldog.common.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EtcController {
    private final EtcService service;

    // 체크아웃 시 리뷰 등록 알림
    @GetMapping("/notificaion")
    public ResVo getReviewEncouragementNotification(){
        return service.getReviewEncouragementNotification();
    }

    // 유저 회원 탈퇴
    @PostMapping("/withdrawal")
    public ResVo postWithdrawal(){
        return service.postWithdrawal();
    }
    // 영웅

}
