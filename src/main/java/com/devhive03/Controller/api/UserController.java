package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.KakaoProfile;
import com.devhive03.Model.DAO.OAuthToken;
import com.devhive03.Model.DAO.User;
import com.devhive03.Service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@JsonIgnoreProperties(ignoreUnknown=true)
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody ResponseEntity<User> kakaoCallback(String code) throws JsonProcessingException { //Data를 리턴해주는 controller 함수

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

        //Gson,Json Simple,ObjectMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        System.out.println("엑세스 토큰:"+oauthToken.getAccess_token());

        //HttpHeader 오브젝트 생성
        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2= new HttpHeaders();
        headers2.add("Authorization","Bearer "+ oauthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");


        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2=
                new HttpEntity<>(headers2);

        //Http 요청하기-post방식으로 -그리고 response 변수와 응답 받음
        //exchange라는 함수는 HttpEntity라는 오브젝트를 넣게 되있음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );
        System.out.println(response2.getBody());

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        //User 오브젝트: username,password,email
        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId() );
        System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());

        //kakao 로그인 할 경우 자동으로 어플에서 아디와 비번만들어서 생성해줌
        System.out.println("어플 유저네임 : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());

        User kakaouser = User.builder()
                .username(kakaoProfile.getProperties().getNickname())
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();

        System.out.println("kakaousername:"+kakaouser.getUsername());
        System.out.println("kakaoemail:"+kakaouser.getEmail());
        //가입자 혹은 비가입자 체크해서 처리
        User originuser = userService.회원찾기(kakaouser.getUsername());

        if(originuser == null) {
            User savedUser = userService.회원가입(kakaouser);
            return ResponseEntity.ok(savedUser);
        }

        return ResponseEntity.ok(originuser);

    }


}
