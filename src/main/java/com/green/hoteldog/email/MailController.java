package com.green.hoteldog.email;

import com.green.hoteldog.common.ResVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {
    private final MailSendService mailSendService;


    @PostMapping("/api/mailSend")
    public ResVo sendEmail(@RequestBody @Valid EmailRequestDto dto){
        System.out.println(dto.getEmail());
        mailSendService.joinEmail(dto.getEmail());
        return new ResVo(1);

    }
    @PostMapping("/api/mailAuthCheck")
    public EmailResponseVo checkMail(@RequestBody @Valid EmailCheckDto dto){
        Boolean checked = mailSendService.CheckAuthNum(dto.getEmail(), dto.getAuthNum());
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
