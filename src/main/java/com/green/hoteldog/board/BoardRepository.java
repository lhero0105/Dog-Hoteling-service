package com.green.hoteldog.board;

import com.green.hoteldog.board.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BoardRepository implements BoardRepositoryRef{
    private final BoardMapper boardMapper;
    public Integer postBoard(PostBoardDto dto) {
        return boardMapper.postBoard(dto);
    }
    public Integer postBoardPics(PostBoardPicDto dto) {
        return boardMapper.postBoardPics(dto);
    }
    public Integer delBoardPics(int boardPk) {
        return boardMapper.delBoardPics(boardPk);
    }
    public Integer putBoard(PutBoardDto dto) {
        return boardMapper.putBoard(dto);
    }
    public Integer delBoard(DeleteBoardDto dto) {
        return boardMapper.delBoard(dto);
    }
    public Integer postComment(PostCommentDto dto) {
        return boardMapper.postComment(dto);
    }
    public Integer delComment(DeleteCommentDto dto) {
        return boardMapper.delComment(dto);
    }
    public List<SimpleBoardVo> getBoardList(GetBoardListDto dto) {
        return boardMapper.getBoardList(dto);
    }
    public GetBoardInfoVo getBoardInfo(int boardPk) {
        return boardMapper.getBoardInfo(boardPk);
    }
    public List<String> selBoardPics(int boardPk) {
        return boardMapper.selBoardPics(boardPk);
    }
    public Integer boardViewCount(int boardPk) {
        return boardMapper.boardViewCount(boardPk);
    }
    public List<CommentInfoVo> selBoardComment(GetBoardCommentDto dto) {
        return boardMapper.selBoardComment(dto);
    }
    public Integer updComment(PutCommentDto dto) {
        return boardMapper.updComment(dto);
    }
    public List<SimpleBoardVo> myPostingBoardList(GetUserBoardListDto dto) {
        return boardMapper.myPostingBoardList(dto);
    }
    public List<UserCommentVo> myPostingCommentList(GetUserCommentListDto dto) {
        return boardMapper.myPostingCommentList(dto);
    }
    public Integer selBoardCount(GetBoardListDto dto) {
        return boardMapper.selBoardCount(dto);
    }
    public Integer selBoardCommentCount(int boardPk) {
        return boardMapper.selBoardCommentCount(boardPk);
    }
    public Integer selUserBoardCount(int userPk) {
        return boardMapper.selUserBoardCount(userPk);
    }
    public Integer selUserCommentCount(int userPk) {
        return boardMapper.selUserCommentCount(userPk);
    }
}
