package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsDetailsResponseDTO;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsRequestDTO;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsResponseDTO;
import com.devhive03.Repository.CommunityPostsDAORepository;
import com.devhive03.Service.CommunityPostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "커뮤니티 게시글", description = "커뮤니티 게시글 API")
@RestController
@RequestMapping("/communityposts")
@RequiredArgsConstructor
public class CommunityPostsController {
    private final CommunityPostsService communityPostsService;

    // Get Post by ID
    @Operation(summary = "커뮤니티ID로 자세한 글내용 받기 + 댓글")
    @GetMapping("/get/{communityPostID}/{userId}")
    public ResponseEntity<CommunityPostsDetailsResponseDTO> getCommunityPostById(@PathVariable Long communityPostID, @RequestParam(required = false) Long userId) {
        return communityPostsService.getCommunityPost(communityPostID, userId);
    }

    // Get Posts by User ID
    @Operation(summary = "내가 쓴 글 리스트 받기")
    @GetMapping("/user/get/{userId}")
    public ResponseEntity<List<CommunityPostsResponseDTO>> getCommunityPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(communityPostsService.getCommunityPostsByUser(userId));
    }

    @Operation(summary = "커뮤니티 조회")
    @GetMapping
    public List<CommunityPostsResponseDTO> getCommunityPosts() {
        return communityPostsService.getAllCommunityPosts();
    }

    // Update a Post by ID
    @Operation(summary = "커뮤니티 글 수정")
    @PutMapping("/put/{communityPostID}")
    public String updateCommunityPost(@PathVariable Long communityPostID, @RequestBody CommunityPostsRequestDTO communityPostRequest) {
        return communityPostsService.updateCommunityPost(communityPostID, communityPostRequest);
    }

    // Create a new Post
    @Operation(summary = "커뮤니티 글 생성")
    @PostMapping("/post")
    public String createCommunityPost(@RequestBody CommunityPostsRequestDTO communityPostRequest) throws Exception {
        return communityPostsService.createCommunityPost(communityPostRequest);
    }


    // Delete a Post
    @Operation(summary = "커뮤니티 글 삭제")
    @DeleteMapping("/delete/{communityPostID}")
    public String deleteCommunityPost(@PathVariable Long communityPostID) {
        return communityPostsService.deleteCommunityPost(communityPostID);
    }




}
