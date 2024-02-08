package com.green.hoteldog.board;

import com.green.hoteldog.BaseIntegrationTest;
import com.green.hoteldog.board.models.GetBoardListDto;
import com.green.hoteldog.board.models.GetSimpleBoardVo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BoardIntegrationTest extends BaseIntegrationTest {
    @Test
    @Rollback
    public void getBoardList() throws Exception{
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("boardCategoryPk", "0");
        params.add("page","1");
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/feed?boardCategoryPk={a}&page={b}",0,1)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        GetSimpleBoardVo vo = objectMapper.readValue(content, GetSimpleBoardVo.class);
        Assert.assertNotNull(vo);
    }
    @Test
    @Rollback
    public void getBoardDetail(){

    }
    @Test
    @Rollback
    public void insBoard(){

    }
    @Test
    @Rollback
    public void putBoard(){

    }
    @Test
    @Rollback
    public void deleteBoard(){

    }
    @Test
    @Rollback
    public void postComment(){

    }
    @Test
    @Rollback
    public void deleteComment(){

    }
    @Test
    @Rollback
    public void updateComment(){

    }
    @Test
    @Rollback
    public void myBoardList(){

    }
    @Test
    @Rollback
    public void myCommentList(){

    }
}
