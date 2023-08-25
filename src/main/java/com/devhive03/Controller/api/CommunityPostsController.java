package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsDetailsResponseDTO;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsRequestDTO;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsResponseDTO;
import com.devhive03.Service.CommunityPostsService;
import io.swagger.v3.oas.annotations.Operation;
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
    @GetMapping("/get/{communityPostID}")
    public ResponseEntity<CommunityPostsDetailsResponseDTO> getCommunityPostById(@PathVariable Long communityPostID) {
        Optional<CommunityPosts> communityPost = communityPostsService.getCommunityPost(communityPostID);

        return communityPost.map(post -> ResponseEntity.ok(convertEntityToDetailsResponseDTO(post)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Get Posts by User ID
    @Operation(summary = "유저ID로 글 리스트 받기")
    @GetMapping("/user/get/{userId}")
    public ResponseEntity<List<CommunityPostsResponseDTO>> getCommunityPostsByUserId(@PathVariable Long userId) {
        List<CommunityPosts> communityPosts = communityPostsService.getCommunityPostsByUser(userId);
        List<CommunityPostsResponseDTO> responseList = communityPosts.stream()
                .map(this::convertEntityToResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }


    // Update a Post by ID
    @Operation(summary = "커뮤니티 글 수정")
    @PutMapping("/put/{communityPostID}")
    public ResponseEntity<CommunityPostsResponseDTO> updateCommunityPost(@PathVariable Long communityPostID, @RequestBody CommunityPostsRequestDTO communityPostRequest) {
        CommunityPosts updatedCommunityPost = communityPostsService.updateCommunityPost(communityPostID, communityPostRequest);

        return ResponseEntity.ok(convertEntityToResponseDTO(updatedCommunityPost));
    }


    // Create a new Post
    @Operation(summary = "커뮤니티 글 생성")
    @PostMapping("/post")
    public ResponseEntity<CommunityPostsResponseDTO> createCommunityPost(@RequestBody CommunityPostsRequestDTO communityPostRequest) throws Exception {
        CommunityPosts savedCommunityPost = communityPostsService.createCommunityPost(communityPostRequest);
        CommunityPostsResponseDTO responseDTO = convertEntityToResponseDTO(savedCommunityPost);

        return ResponseEntity.ok(responseDTO);
    }





    // Delete a Post
    @Operation(summary = "커뮤니티 글 삭제")
    @DeleteMapping("/delete/{communityPostID}")
    public ResponseEntity<?> deleteCommunityPost(@PathVariable Long communityPostID) {
        communityPostsService.deleteCommunityPost(communityPostID);
        return ResponseEntity.ok().build();
    }


    private CommunityPostsResponseDTO convertEntityToResponseDTO(CommunityPosts communityPost) {
        CommunityPostsResponseDTO dto = new CommunityPostsResponseDTO();

        // Set basic details
        dto.setCommunityPostID(communityPost.getCommunityPostID());
        dto.setCommunityPostTitle(communityPost.getCommunityPostTitle());
        dto.setCommunityPostContent(communityPost.getCommunityPostContent());
        dto.setCommunityPostDate(communityPost.getCommunityPostDate());
        dto.setCommunityPostLikesCount(communityPost.getCommunityPostLikes());
        dto.setIsCommunityPostLikes(communityPost.getIsCommunityPostLikes());

        // Set Writer details
        CommunityPostsResponseDTO.Writer writerDTO = new CommunityPostsResponseDTO.Writer();
        writerDTO.setId(communityPost.getWriter().getId());
        writerDTO.setUsername(communityPost.getWriter().getUsername());
        writerDTO.setLoginId(communityPost.getWriter().getLoginId());
        dto.setWriter(writerDTO);

        // Set Comments IDs
        List<Long> commentIDs = communityPost.getComments().stream()
                .map(Comments::getCommentsID)
                .collect(Collectors.toList());
        dto.setCommentIDs(commentIDs);

        return dto;
    }


    private CommunityPostsDetailsResponseDTO convertEntityToDetailsResponseDTO(CommunityPosts communityPost) {
        CommunityPostsDetailsResponseDTO dto = new CommunityPostsDetailsResponseDTO();

        dto.setCommunityPostID(communityPost.getCommunityPostID());
        dto.setCommunityPostTitle(communityPost.getCommunityPostTitle());
        dto.setCommunityPostContent(communityPost.getCommunityPostContent());
        dto.setCommunityPostDate(communityPost.getCommunityPostDate());
        dto.setCommunityPostLikesCount(communityPost.getCommunityPostLikes());
        dto.setIsCommunityPostLikes(communityPost.getIsCommunityPostLikes());

        // Set Writer details
        CommunityPostsDetailsResponseDTO.Writer writerDTO = new CommunityPostsDetailsResponseDTO.Writer();
        writerDTO.setId(communityPost.getWriter().getId());
        writerDTO.setUsername(communityPost.getWriter().getUsername());
        writerDTO.setLoginId(communityPost.getWriter().getLoginId());
        dto.setWriter(writerDTO);

        // Set Comments
        List<CommunityPostsDetailsResponseDTO.Comment> commentsDTOs = communityPost.getComments().stream()
                .map(comment -> {
                    CommunityPostsDetailsResponseDTO.Comment commentDTO = new CommunityPostsDetailsResponseDTO.Comment();
                    commentDTO.setCommentsID(comment.getCommentsID());

                    CommunityPostsDetailsResponseDTO.Comment.User userDTO = new CommunityPostsDetailsResponseDTO.Comment.User();
                    userDTO.setId(comment.getUser().getId());
                    userDTO.setUsername(comment.getUser().getUsername());
                    userDTO.setLoginId(comment.getUser().getLoginId());

                    commentDTO.setUser(userDTO);
                    commentDTO.setCommentContent(comment.getCommentContent());
                    commentDTO.setCommentDate(comment.getCommentDate());
                    return commentDTO;
                })
                .collect(Collectors.toList());
        dto.setComments(commentsDTOs);

        return dto;
    }

}
