package com.green.hoteldog.review.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReviewInsDto {
    @JsonIgnore
    private int reviewPk;
    @JsonIgnore
    private int userPk;
    @Schema(description = "유저가 예약한 예약pk")
    private int resPk;
    @Schema(description = "리뷰 코멘트")
    private String comment;
    @Min(value = 1,message = "평점은 최소 1 최대 10의 값을 보내야 합니다")
    @Max(value = 10,message = "평점은 최소 1 최대 10의 값을 보내야 합니다")
    @Schema(description = "리뷰 평정 1~10점까지 가능")
    private int score;
    @JsonIgnore
    private List<MultipartFile> pics;

}
