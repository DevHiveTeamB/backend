package com.devhive03.Controller.api;

import org.antlr.v4.runtime.Token;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
public class UserController {

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){ //Data를 리턴해주는 controller 함수

        //post방식으로 key=value 데이터를 요청(카카오쪽으로)

        //HttpHeader 오브젝트 생성
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","1954fa99c4e993dc0ea405323d7f3bad");
        params.add("redirect_url"," http://localhost:8080/auth/kakao/callback");
        params.add("code",code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest=
            new HttpEntity<>(params,headers);

        //Http 요청하기-post방식으로 -그리고 response 변수와 응답 받음
        //exchange라는 함수는 HttpEntity라는 오브젝트를 넣게 되있음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token ",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class

        );
        //response에는 http status code,헤더정보,실제 데이터가 존재하는 body정보를 확인 할 수 있음
        return "kakao 토큰요청 완료: 토큰요청에 대한 응답:"+response;
    }
}
