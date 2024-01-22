package com.green.hoteldog.review;

import com.green.hoteldog.common.MyFileUtils;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.review.models.*;
import com.green.hoteldog.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReveiwService {
    private final ReviewMapper mapper;
    private final AuthenticationFacade facade;
    private final MyFileUtils fileUtils;

    //리뷰 등록
    public ResVo insReview(ReviewInsDto dto) {
        try {
            mapper.insReview(dto);
        }catch (Exception e){
            return new ResVo(0);
        }
        List<String> pics = new ArrayList<>();
        String target = "/review/"+dto.getReviewPk();
        fileUtils.delFiles(target);
        for(MultipartFile file : dto.getPics()){
            String saveFileNm = fileUtils.transferTo(file,target);
            pics.add(saveFileNm);
        }
        ReviewInsPicsDto picsDto = new ReviewInsPicsDto();
        picsDto.setReviewPk(dto.getReviewPk());
        picsDto.setPics(pics);
        mapper.insReviewPics(picsDto);
        return new ResVo(1);
    }

    //리뷰 전체 수정
    public ResVo putReview(ReviewUpdDto dto) {
        dto.setUserPk(facade.getLoginUserPk());
        try {
            mapper.updReview(dto);
            mapper.delReviewPics(dto);
        } catch (Exception e) {
            return new ResVo(0);
        }
        ReviewInsPicsDto picsDto = new ReviewInsPicsDto();
        List<String> pics = new ArrayList<>();
        String target = "/review/"+dto.getReviewPk();
        fileUtils.delFolderTrigger(target);
        for(MultipartFile file : dto.getPics()){
            String saveFileNm = fileUtils.transferTo(file,target);
            pics.add(saveFileNm);
        }
        picsDto.setReviewPk(dto.getReviewPk());
        picsDto.setPics(pics);
        mapper.insReviewPics(picsDto);
        return new ResVo(1);

    }

    //리뷰 코멘트 수정
    public ResVo patchReviewComment(ReviewPatchDto dto) {
        try {
            mapper.updReviewComment(dto);
            return new ResVo(1);
        } catch (Exception e) {
            return new ResVo(0);
        }
    }

    //리뷰 좋아요 토글
    public ResVo patchReviewFav(int reviewPk) {
        ReviewFavDto dto = new ReviewFavDto();
        dto.setReviewPk(reviewPk);
        dto.setUserPk(facade.getLoginUserPk());

        if (mapper.delReviewFav(dto) == 0) {
            int result = mapper.insReviewFav(dto);
            return new ResVo(result);
        }
        return new ResVo(2);
    }

    //호텔 리뷰 리스트
    public List<HotelReviewSelVo> hotelReviewList(HotelReviewSelDto dto) {
        List<HotelReviewSelVo> reviewList = mapper.selHotelReviewList(dto);

        if (reviewList.size() > 0) {
            List<Integer> reviewPkList = new ArrayList<>();
            Map<Integer, HotelReviewSelVo> reviewSelVoMap = new HashMap<>();
            for (HotelReviewSelVo vo : reviewList) {
                reviewPkList.add(vo.getReviewPk());
                reviewSelVoMap.put(vo.getReviewPk(), vo);
            }
            List<ReviewPicVo> picVoList = mapper.selReviewPics(reviewPkList);
            for (ReviewPicVo vo : picVoList) {
                HotelReviewSelVo selVo = reviewSelVoMap.get(vo.getReviewPk());
                if (selVo.getPics() == null) {
                    selVo.setPics(new ArrayList<>());
                }
                selVo.getPics().add(vo.getPic());
            }
        }
        return reviewList;
    }
    //재웅

    public List<HotelReviewSelVo> getHotelReview(HotelReviewSelDto dto){
        // n+1 이슈 해결
        List<HotelReviewSelVo> list = mapper.selHotelReview(dto);
        for ( HotelReviewSelVo vo : list ) {
            dto.getReviewPk().add(vo.getReviewPk());
        }
        List<HotelReviewPicsSelVo> pics = mapper.selHotelReviewPics(dto);

        // pk를 담을 list, pk 및 해당 객체 주솟 값을 담을 map 생성
        List<Integer> revPk = new ArrayList<>();
        Map<Integer, HotelReviewSelVo> hashMap = new HashMap<>();
        for ( HotelReviewSelVo vo : list ) {
            revPk.add(vo.getReviewPk());
            hashMap.put(vo.getReviewPk(), vo);
        }
        for ( HotelReviewPicsSelVo vo : pics ) {
            hashMap.get(vo.getReviewPk()).getPics().add(vo.getPic());
        }
        return list;
    }
    //영웅
}