package com.green.hoteldog.etc;

import com.green.hoteldog.common.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EtcService {

    // 체크아웃 시 리뷰 등록 알림
    public ResVo getReviewEncouragementNotification(){
        return null;
    }

    // 유저 회원 탈퇴
    public ResVo postWithdrawal(){
        return null;
    }
    // 영웅
}
