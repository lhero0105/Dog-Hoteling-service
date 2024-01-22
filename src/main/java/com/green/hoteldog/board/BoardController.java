package com.green.hoteldog.board;

import com.green.hoteldog.board.models.*;
import com.green.hoteldog.common.ResVo;
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
    public List<GetSimpleBoardVo> getBoardList(GetBoardListDto dto){
        return service.getBoardList(dto);
    }
    //게시글 세부내용
    @GetMapping("/view")
    public GetBoardInfoVo getBoardDetail (GetBoardcommentDto dto) {
        return service.getBoardInfo(dto);
    }
    //게시글 등록
    @PostMapping
    public ResVo postBoard(@RequestPart List<MultipartFile> pics
                          ,@RequestBody PostBoardDto dto){
        dto.setPics(pics);
        return service.postBoard(dto);
    }
    //게시글 수정
    @PutMapping
    public ResVo putBoard(@RequestPart List<MultipartFile> pics, @RequestBody PutBoardDto dto){
        dto.setPisc(pics);
        return service.putBoard(dto);
    }
    //게시글 좋아요
    @GetMapping("/fav")
    public ResVo patchBoard(BoardFavDto dto){
        return service.putBoardFav(dto);
    }
    //게시글 삭제
    @DeleteMapping
    public ResVo deleteBoard(@RequestBody DeleteBoardDto dto){
        return service.deleteBoard(dto);
    }
    //댓글 작성
    @PostMapping("/comment")
    public ResVo postComment(@RequestBody PostCommentDto dto){
        return service.postComment(dto);
    }
    //댓글 삭제
    @DeleteMapping("/comment")
    public ResVo deleteComment(@RequestBody DeleteCommentDto dto){
        return service.deleteComment(dto);
    }

    //댓글 수정
    @PatchMapping("/comment")
    public ResVo updateComment(@RequestBody PutCommentDto dto){
        return service.updateComment(dto);
    }

    //내가 쓴 글 보기
    @GetMapping("/my-board")
    public List<GetSimpleBoardVo> myBoardList(){
        return service.userPostingBoradList();
    }
    //내가 작성한 댓글 보기
    @GetMapping("my-comment")
    public List<GetUserCommentListVo> myCommentList(){
        return service.userPostingCommentList();
    }
}
