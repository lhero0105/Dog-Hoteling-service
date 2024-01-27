package com.green.hoteldog.board;

import com.green.hoteldog.board.models.*;
import com.green.hoteldog.common.MyFileUtils;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardMapper mapper;
    private final MyFileUtils fileUtils;
    private final AuthenticationFacade facade;
    //게시글 등록 2024-01-18수정

    public ResVo postBoard(PostBoardDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        if(dto.getUserPk() == 0){
            //예외처리 로그인 하지 않은 유저는 글 작성 안됨
        }
        log.info("postDto : {}",dto);
        try {
            mapper.postBoard(dto);
        }catch (Exception e){
            return new ResVo(0);
        }
        if(dto.getPics() != null){
            List<String> pics = new ArrayList<>();
            String target = "/board/"+dto.getBoardPk();
            for(MultipartFile file : dto.getPics()){
                String saveFileNm = fileUtils.transferTo(file,target);
                pics.add(saveFileNm);
            }
            PostBoardPicDto picsDto = new PostBoardPicDto();
            picsDto.setBoardPk(dto.getBoardPk());
            picsDto.setPics(pics);
            try {
                mapper.postBoardPics(picsDto);
                return new ResVo(1);
            }catch (Exception e){
                return new ResVo(0);
            }
        }
        return new ResVo(1);
    }
    //게시글 등록 2024-01-18수정

    //게시글 수정
    public ResVo putBoard(PutBoardDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        int result = mapper.putBoard(dto);
        if (result == 0){
            return new ResVo(0);
        }
        String target = "/board/"+dto.getBoardPk();
        try {
            fileUtils.delFolderTrigger(target);
            mapper.delBoardPics(dto.getBoardPk());
        }catch (Exception e){
            return new ResVo(0);
        }
        if(dto.getPisc() != null){
            List<String> pics = new ArrayList<>();
            for(MultipartFile file : dto.getPisc()){
                String saveFileNm = fileUtils.transferTo(file,target);
                pics.add(saveFileNm);
            }

            PostBoardPicDto picsDto = new PostBoardPicDto();
            picsDto.setBoardPk(dto.getBoardPk());
            picsDto.setPics(pics);
            try {
                mapper.postBoardPics(picsDto);
                return new ResVo(1);
            }catch (Exception e){
                return new ResVo(0);
            }
        }
        return new ResVo(1);


    }
    //게시글 수정

    /*//게시글 좋아요 임시 중단
    public ResVo putBoardFav(BoardFavDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        if(dto.getUserPk() == 0){
            //예외처리 로그인 하지 않은 유저는 좋아요 할 수 없음
        }
        if(mapper.delFav(dto) == 0){
            mapper.postFav(dto);
            return new ResVo(1);
        }
        return new ResVo(2);
    }
    //게시글 좋아요*/

    //게시글 삭제
    public ResVo deleteBoard(DeleteBoardDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        if(dto.getUserPk() == 0){
            //예외처리 로그인 하지 않은 유저는 좋아요 할 수 없음
        }
        int result = mapper.delBoard(dto);
        return new ResVo(result);
    }
    //게시글 삭제

    //댓글 등록
    public ResVo postComment(PostCommentDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        if(dto.getUserPk() == 0){
            //예외처리 로그인 하지 않은 유저는 좋아요 할 수 없음
        }
        int result = mapper.postComment(dto);
        return new ResVo(result);
    }
    //댓글 등록

    //댓글 삭제
    public ResVo deleteComment(DeleteCommentDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        if(dto.getUserPk() == 0){
            //예외처리 로그인 하지 않은 유저는 좋아요 할 수 없음
        }
        int result = mapper.delComment(dto);
        return new ResVo(result);
    }
    //댓글 삭제

    //댓글 수정
    public ResVo updateComment(PutCommentDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        if(dto.getUserPk() == 0){
            //예외처리 로그인 하지 않은 유저는 좋아요 할 수 없음
        }
        int result = mapper.updComment(dto);
        return new ResVo(result);
    }
    //댓글 수정

    //게시글 리스트
    public List<GetSimpleBoardVo> getBoardList(GetBoardListDto dto){
        return mapper.getBoardList(dto);
    }
    //게시글 리스트

    //게시글 정보
    public GetBoardInfoVo getBoardInfo(GetBoardInfoDto dto){
        GetBoardInfoVo vo = mapper.getBoardInfo(dto.getBoardPk());
        vo.setPics(mapper.selBoardPics(dto.getBoardPk()));
        vo.setComments(mapper.selBoardComment(dto));
        mapper.boardViewCount(dto.getBoardPk());
        return vo;
    }
    //게시글 정보

    //로그인 유저가 작성한 게시글
    public List<GetSimpleBoardVo> userPostingBoradList(){
        if(facade.getLoginUserPk() == 0){
            //예외처리 로그인 하지 않은 유저는 좋아요 할 수 없음
        }
        return mapper.myPostingBoardList(facade.getLoginUserPk());
    }
    //로그인 유저가 작성한 게시글

    //로그인 유저가 작성한 댓글
    public List<GetUserCommentListVo> userPostingCommentList(){
        if(facade.getLoginUserPk() == 0){
            //예외처리 로그인 하지 않은 유저는 좋아요 할 수 없음
        }
        return mapper.myPostingCommentList(facade.getLoginUserPk());
    }
    //로그인 유저가 작성한 댓글

}
