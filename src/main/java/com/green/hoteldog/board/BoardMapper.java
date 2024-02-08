package com.green.hoteldog.board;

import com.green.hoteldog.board.models.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    Integer postBoard(PostBoardDto dto);
    Integer postBoardPics(PostBoardPicDto dto);
    Integer delBoardPics(int boardPk);
    Integer putBoard(PutBoardDto dto);
    /*
    임시중단
    int postFav(BoardFavDto dto);
    int delFav(BoardFavDto dto);
    */
    Integer delBoard(DeleteBoardDto dto);
    Integer postComment(PostCommentDto dto);
    Integer delComment(DeleteCommentDto dto);
    List<SimpleBoardVo> getBoardList(GetBoardListDto dto);
    GetBoardInfoVo getBoardInfo(int boardPk);
    List<String> selBoardPics(int boardPk);
    Integer boardViewCount(int boardPk);
    List<CommentInfoVo> selBoardComment(GetBoardCommentDto dto);
    Integer updComment(PutCommentDto dto);
    List<SimpleBoardVo> myPostingBoardList(GetUserBoardListDto dto);
    List<UserCommentVo> myPostingCommentList(GetUserCommentListDto dto);
    Integer selBoardCount(GetBoardListDto dto);
    Integer selBoardCommentCount(int boardPk);
    Integer selUserBoardCount(int userPk);
    Integer selUserCommentCount(int userPk);
}
