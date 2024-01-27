package com.green.hoteldog.email;

import com.green.hoteldog.common.RedisUtil;
import com.green.hoteldog.common.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class MailController {
    private final MailSendService mailSendService;
    private final RedisUtil redisUtil;



    @PostMapping("/mailSend")
    @Operation(summary = "이메일 인증 보내기",description = "이메일 인증 보내기")
    public ResVo sendEmail(@RequestBody @Valid EmailRequestDto dto){
        if(mailSendService.checkDuplicationEmail(dto.getEmail())){
            return new ResVo(0);//이미 db에 등록된 email
        }
        redisUtil.deleteData(dto.getEmail());
        System.out.println(dto.getEmail());
        mailSendService.joinEmail(dto.getEmail());
        return new ResVo(1);

    }
    @PostMapping("/mailAuthCheck")
    @Operation(summary = "이메일 인증번호 체크",description = "이메일 인증번호 체크")
    public EmailResponseVo checkMail(@RequestBody @Valid EmailCheckDto dto){
        Boolean checked = mailSendService.checkAuthNum(dto.getEmail(), dto.getAuthNum());
        EmailResponseVo vo = new EmailResponseVo();
        if(checked){
            vo.setEmail(dto.getEmail());
            vo.setResult(1);
            return vo;
        }
        else {
            vo.setEmail(dto.getEmail());
            vo.setResult(0);
            return vo;
        }
    }


}
