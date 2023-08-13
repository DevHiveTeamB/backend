package com.devhive03.Controller.api;

import com.devhive03.Controller.ExceptionControll.ResourceNotFoundException;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.User.UserDTO;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// 경로 설정
@Tag(name = "사용자", description = "유저 API")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAORepository userDAORepository;

    //CORS 정책을 해결하기 위한 어노테이션
    //모든 요청에 대해 허용
    @CrossOrigin(origins = "*")
    @GetMapping("/{userId}")
    @Operation(summary = "유저 조회", description = "유저 조회")
    @ApiResponse(responseCode = "200", description = "유저 조회 성공")
    @ApiResponse(responseCode = "400", description = "유저 조회 실패", content = @Content(examples = @ExampleObject(value = "{\n" +
            "  \"message\": \"User not found with id 1\",\n" +
            "}")))
    public @ResponseBody ResponseEntity<UserDTO> findUser(@PathVariable Long userId) {
        // user id로 user 찾기
        Optional<User> user = userDAORepository.findById(userId);
        // user가 있는지 확인
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User ID : "+userId+" not found");
        }
        return ResponseEntity.ok(UserDTO.of(user.get()));
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    @Operation(summary = "유저 업데이트", description = "주어진 유저 정보로 유저 업데이트")
    @ApiResponse(responseCode = "200", description = "유저 업데이트 성공")
    @ApiResponse(responseCode = "400", description = "유저 업데이트 실패", content = @Content(examples = @ExampleObject(value = "{\n" +
            "  \"message\": \"User not found with id 1\",\n" +
            "}")))
    public ResponseEntity<UserDTO> updateUserData(@Valid @RequestBody UserDTO userDTO) {
        Optional<User> findUser = userDAORepository.findById(userDTO.getId());
        if (findUser.isEmpty()) {
            throw new ResourceNotFoundException("User ID : "+userDTO.getId()+" not found");
        }
        return ResponseEntity.ok(UserDTO.of(userDAORepository.save(findUser.get().update(userDTO))));
    }
}
