package com.green.hoteldog.board;

import com.green.hoteldog.board.models.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int postBoard(PostBoardDto dto);
    int postBoardPics(PostBoardPicDto dto);
    int delBoardPics(int boardPk);
    int putBoard(PutBoardDto dto);
    /*
    임시중단
    int postFav(BoardFavDto dto);
    int delFav(BoardFavDto dto);
    */
    int delBoard(DeleteBoardDto dto);
    int postComment(PostCommentDto dto);
    int delComment(DeleteCommentDto dto);
    List<GetSimpleBoardVo> getBoardList(GetBoardListDto dto);
    GetBoardInfoVo getBoardInfo(int boardPk);
    List<String> selBoardPics(int boardPk);
    int boardViewCount(int boardPk);
    List<CommentInfoVo> selBoardComment(GetBoardInfoDto dto);
    int updComment(PutCommentDto dto);
    List<GetSimpleBoardVo> myPostingBoardList(int userPk);
    List<GetUserCommentListVo> myPostingCommentList(int userPk);
}
