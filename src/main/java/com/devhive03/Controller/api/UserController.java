package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.User.UserByUsernameDTO;
import com.devhive03.Model.DTO.User.UserUpdateDTO;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// 경로 설정
@RestController("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAORepository userDAORepository;

    //CORS 정책을 해결하기 위한 어노테이션
    //모든 요청에 대해 허용
    @CrossOrigin(origins = "*")
    @GetMapping("/user/{username}")
    public @ResponseBody ResponseEntity<UserByUsernameDTO> findUser(@PathVariable String username){
        Optional<User> user = userDAORepository.findByUsername(username);
        // user가 있는지 확인
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User findUser = user.get();
        UserByUsernameDTO userByUsernameDTO = UserByUsernameDTO.builder()
                .id(findUser.getId())
                .username(findUser.getUsername())
                .loginId(findUser.getLoginId())
                .email(findUser.getEmail())
                .phoneNumber(findUser.getPhoneNumber())
                .profilePhoto(findUser.getProfilePhoto())
                .introduction(findUser.getIntroduction())
                .membership(findUser.getMembership())
                .certification(findUser.getCertification())
                .ratingState(findUser.getRatingState())
                .build();

        return ResponseEntity.ok(userByUsernameDTO);
    }


    //
    @CrossOrigin(origins = "*")
    @PutMapping("/user/data/put")
    public ResponseEntity<UserUpdateDTO> updateUserData(@Valid @RequestBody UserUpdateDTO userUpdateDTO){
        Optional<User> findUser = userDAORepository.findByUsername(userUpdateDTO.getUsername());
        if(findUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User updateUser = findUser.get();
        updateUser.setUsername(userUpdateDTO.getUsername());
        updateUser.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        updateUser.setProfilePhoto(userUpdateDTO.getProfilePhoto());
        updateUser.setIntroduction(userUpdateDTO.getIntroduction());
        //store string to byte
        updateUser.setMembership(Byte.parseByte(userUpdateDTO.getMembership()));
        updateUser.setCertification(Byte.parseByte(userUpdateDTO.getCertification()));
        updateUser.setRatingState(Byte.parseByte(userUpdateDTO.getRatingState()));
        userDAORepository.save(updateUser);
        return ResponseEntity.ok(userUpdateDTO);
    }
}
