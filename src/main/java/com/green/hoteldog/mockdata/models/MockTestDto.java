package com.green.hoteldog.mockdata.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MockTestDto {
    private int type;
    private int page;
    private int rowCount;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private List<MultipartFile> pics;

    public void setPage(int page){
        this.startIdx = (page - 1)* rowCount;
        this.page = page;
    }
}
