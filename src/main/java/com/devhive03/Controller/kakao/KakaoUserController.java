package com.devhive03.Controller.kakao;

import com.devhive03.Model.DAO.KakaoProfile;
import com.devhive03.Model.DAO.OAuthToken;
import com.devhive03.Model.DAO.User;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;


@JsonIgnoreProperties(ignoreUnknown = true)
// 경로 설정
@Controller
public class KakaoUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAORepository userDAORepository;


    // 카카오 로그인 요청시 실행되는 콜백 API
    // 쿼리 스트링으로 인증 코드를 전달 받는다.
    @GetMapping("/auth/kakao/callback")
    public RedirectView kakaoCallback(@RequestParam String code) throws JsonProcessingException { //Data를 리턴해주는 controller 함수

        System.out.println("카카오 코드:" + code);


        //카카오에게 토큰 요청하기
        //https://kauth.kakao.com/oauth/token 에 POST 방식으로 요청 (카카오가 요구하는 방식)
        //RestTemplate을 사용하여 Http 요청하기
        RestTemplate rt = new RestTemplate();
        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "1954fa99c4e993dc0ea405323d7f3bad");
        params.add("redirect_url", " http://localhost:8080/auth/kakao/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);
        // 응답 요청하기 - POST 방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        System.out.println("response.getBody() = " + response.getBody());


        //ObjectMapper로 JSON 데이터를 자바 오브젝트로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);

        System.out.println("엑세스 토큰:" + oauthToken.getAccess_token());


        //사용자 정보 조회
        RestTemplate rt2 = new RestTemplate();
        //HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
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

        KakaoProfile kakaoProfile = new ObjectMapper().readValue(response2.getBody(), KakaoProfile.class);

        //User 오브젝트: username,password,email
        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        //kakao 로그인 할 경우 자동으로 어플에서 아디와 비번만들어서 생성해줌

        //가입자 혹은 비가입자 체크
        Optional<User> user = userDAORepository.findByKakaoId(kakaoProfile.getId());
        User realUser = null;
        Long userId = 0L;
        //기존 회원일 경우
        if (user.isPresent()) {
            System.out.println("기존 회원입니다.");
            realUser = user.get();
            userId = realUser.getId();
        }
        //새로운 가입자일경우 데이터베이스 저장
        else {
            System.out.println("기존 회원이 아닙니다.");

            User newUser = new User();
            newUser.setKakaoId(kakaoProfile.getId());
            newUser.setUsername(kakaoProfile.getProperties().getNickname());
            newUser.setEmail(kakaoProfile.getKakao_account().getEmail());
            newUser.setProfilePhoto(kakaoProfile.getProperties().getProfile_image());

            realUser = userDAORepository.save(newUser);
            userId = realUser.getId();
        }

        //로그인 완료 화면으로 리다이렉트
        RedirectView redirectView = new RedirectView("http://localhost:8081/about");
        //쿼리스트링으로 UserID를 보내줌
        redirectView.addStaticAttribute("userId", userId);

        return redirectView;
    }
}
