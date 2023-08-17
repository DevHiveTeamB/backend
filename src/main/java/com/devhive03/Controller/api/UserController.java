package com.devhive03.Controller.api;

import com.devhive03.Controller.ExceptionControll.ResourceNotFoundException;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.User.UserDTO;
import com.devhive03.Repository.PostDAORepository;
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

import java.util.List;
import java.util.Map;
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

    @Autowired
    private PostDAORepository postDAORepository;

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
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User ID : " + userId + " not found");
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
        Optional<User> findUser = userDAORepository.findById(userDTO.getUserId());
        if (findUser.isEmpty()) {
            throw new ResourceNotFoundException("User ID : " + userDTO.getUserId() + " not found");
        }
        return ResponseEntity.ok(UserDTO.of(userDAORepository.save(findUser.get().update(userDTO))));
    }

    //유저 삭제
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{userId}")
    @Operation(summary = "유저 삭제", description = "유저 삭제")
    @ApiResponse(responseCode = "200", description = "유저 삭제 성공")
    @ApiResponse(responseCode = "400", description = "유저 삭제 실패", content = @Content(examples = @ExampleObject(value = "{\n" +
            "  \"message\": \"User not found with id 1\",\n" +
            "}")))
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userId) {
        Optional<User> findUser = userDAORepository.findById(userId);
        if (findUser.isEmpty()) {
            throw new ResourceNotFoundException("User ID : " + userId + " not found");
        }
        User user = findUser.get();

        //게시글이 존재하면 게시글 먼저 지우라고 알려주기
        List<Post> posts = postDAORepository.findAllByWriterId(userId);
        if (!posts.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "게시글이 존재합니다. 게시글을 먼저 삭제해주세요."));
        }

        userDAORepository.delete(user);
        return ResponseEntity.ok(Map.of("message", "유저 삭제 성공"));
    }
}
