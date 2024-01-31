package com.green.hoteldog.board;

import com.green.hoteldog.board.models.*;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.exceptions.BoardErrorCode;
import com.green.hoteldog.exceptions.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService service;

    //게시글 리스트
    @GetMapping
    @Operation(summary = "게시글 리스트", description = "게시글 리스트<br>searchType : 0 = 제목 검색<br>searchType : 1 = 내용 검색<br>searchType : 2 = 닉네임 검색")
    public GetSimpleBoardVo getBoardList(@Valid GetBoardListDto dto){
        log.info("GetBoardListDto dto : {}",dto);
        return service.getBoardList(dto);
    }
    //게시글 세부내용
    @GetMapping("/view")
    @Operation(summary = "게시글 정보",description = "게시글 정보")
    public GetBoardInfoVo getBoardDetail (@Valid GetBoardInfoDto dto) {
        return service.getBoardInfo(dto);
    }
    //게시글 등록
    @PostMapping()
    @Operation(summary = "게시글 등록",description = "게시글 등록<br>이미지 등록은 postman 을 통해서 가능")
    public ResVo insBoard(@RequestPart(required = false) List<MultipartFile> pics
            ,@RequestPart @Valid PostBoardDto dto){

        log.info("controller insDto : {}",dto);
        if(pics != null){
            dto.setPics(pics);
            if (dto.getPics().size() > 3){
                throw new CustomException(BoardErrorCode.PICS_SIZE_OVER);
            }
        }
        return service.postBoard(dto);
    }
    //게시글 검색

    //게시글 수정
    @PutMapping
    @Operation(summary = "게시글 수정",description = "게시글 수정<br>이미지 등록은 postman 을 통해서 가능")
    public ResVo putBoard(@RequestPart(required = false) List<MultipartFile> pics
            , @RequestPart @Valid PutBoardDto dto){
        if(pics != null){
            dto.setPics(pics);
            if (dto.getPics().size() > 3){
                throw new CustomException(BoardErrorCode.PICS_SIZE_OVER);
            }
        }
        return service.putBoard(dto);
    }
    /*
    //게시글 좋아요 임시 중단
    @GetMapping("/fav")
    public ResVo patchBoard(@Valid BoardFavDto dto){
        return service.putBoardFav(dto);
    }
    */

    //게시글 삭제
    @DeleteMapping
    @Operation(summary = "게시글 삭제",description = "게시글 삭제")
    public ResVo deleteBoard(@Valid DeleteBoardDto dto){
        return service.deleteBoard(dto);
    }
    //댓글 작성
    @PostMapping("/comment")
    @Operation(summary = "댓글 작성",description = "댓글 작성")
    public ResVo postComment(@RequestBody @Valid PostCommentDto dto){
        return service.postComment(dto);
    }
    //댓글 삭제
    @DeleteMapping("/comment")
    @Operation(summary = "댓글 삭제",description = "댓글 삭제")
    public ResVo deleteComment(@Valid DeleteCommentDto dto){
        return service.deleteComment(dto);
    }

    //댓글 수정
    @PatchMapping("/comment")
    @Operation(summary = "댓글 수정",description = "댓글 수정")
    public ResVo updateComment(@RequestBody @Valid PutCommentDto dto){
        return service.updateComment(dto);
    }

    //내가 쓴 글 보기
    @GetMapping("/my-board")
    @Operation(summary = "내가 쓴 글 보기",description = "내가 쓴 글 보기")
    public GetSimpleBoardVo myBoardList(@Valid GetUserBoardListDto dto){
        return service.userPostingBoardList(dto);
    }
    //내가 작성한 댓글 보기
    @GetMapping("/my-comment")
    @Operation(summary = "내가 쓴 댓글 보기",description = "내가 쓴 댓글 보기")
    public GetUserCommentVo myCommentList(@Valid GetUserCommentListDto dto){
        return service.userPostingCommentList(dto);
    }
}
