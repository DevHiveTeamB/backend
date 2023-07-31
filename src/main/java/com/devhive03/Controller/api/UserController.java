package com.devhive03.Controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){ //Data를 리턴해주는 controller 함수
        return "kakao 인증 완료: code 값:"+code; //response 타입을 코드로 지정해주어서 피라미터 안붙여도 가능
    }
}
