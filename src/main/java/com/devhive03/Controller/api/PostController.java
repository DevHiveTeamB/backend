package com.devhive03.Controller.api;

import com.devhive03.Controller.ExceptionControll.ResourceNotFoundException;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.Post.PostDTO;
import com.devhive03.Model.DTO.Post.PostItemDTO;
import com.devhive03.Model.DTO.Post.PostParamsDTO;
import com.devhive03.Model.DTO.Post.PostsDTO;
import com.devhive03.Model.DTO.User.UserWriterDTO;
import com.devhive03.Repository.FavoritesDAORepository;
import com.devhive03.Repository.PostDAORepository;
import com.devhive03.Repository.PostLikesListDAORepository;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostDAORepository postDAORepository;
    private final UserDAORepository userDAORepository;
    private final PostLikesListDAORepository postLikesListDAORepository;
    private final FavoritesDAORepository favoritesDAORepository;

    // Get Post by ID
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Long postId, @RequestParam(required = false) Long userId) {
        //fetch join으로 모든 정보 한번에 조회
        Optional<Post> findPost = postDAORepository.findPostId(postId);
        if (findPost.isEmpty()) {
            throw new ResourceNotFoundException("Post not found with id " + postId);
        }

        Post post = findPost.get();
        //PostDTO로 변환
        PostDTO postDTO = PostDTO.of(post);

        //userId가 null이 아니면 좋아요, 찜 null
        if (userId != null) {
            //좋아요 여부
            boolean isLike = post.getPostLikesLists().stream().anyMatch(postLikesList -> postLikesList.getUser().getId().equals(userId));
            postDTO.setIsLike(isLike);

            //찜 여부
            boolean isFavorite = post.getFavorites().stream().anyMatch(favorites -> favorites.getUser().getId().equals(userId));
            postDTO.setIsFavorite(isFavorite);
        }

        return ResponseEntity.ok(postDTO);
    }

    // Get Posts by WriterId
    @GetMapping("/writer/{userId}")
    public ResponseEntity<PostsDTO> getAllPostBy(@PathVariable("userId") Long userID) {
        List<Post> posts = postDAORepository.findAllByWriterId(userID);

        Optional<User> findWriter = userDAORepository.findById(userID);

        if(findWriter.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id " + userID);
        }

        UserWriterDTO userWriterDTO = UserWriterDTO.of(findWriter.get());

        List<PostItemDTO> postItemDTOS = posts.stream().map(PostItemDTO::of).collect(Collectors.toList());

        PostsDTO postsDTO = new PostsDTO(userWriterDTO, postItemDTOS);
        return ResponseEntity.ok(postsDTO);
    }

    // Get All Posts
    @GetMapping
    public ResponseEntity<List<PostItemDTO>> getAllPosts(PostParamsDTO postParamsDTO) {

        List<Post> posts = postDAORepository.findAllByPostParamsDTO(postParamsDTO.getPostTitle(), postParamsDTO.getLectureName(), postParamsDTO.getMajor(), postParamsDTO.getProfessor());

        List<PostItemDTO> postItemDTOS = posts.stream().map(PostItemDTO::of).collect(Collectors.toList());

        return ResponseEntity.ok(postItemDTOS);
    }
}
