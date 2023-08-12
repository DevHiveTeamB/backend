package com.devhive03.Controller.api;

import com.devhive03.Controller.ExceptionControll.ResourceNotFoundException;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DTO.Post.PostDTO;
import com.devhive03.Repository.FavoritesDAORepository;
import com.devhive03.Repository.PostDAORepository;
import com.devhive03.Repository.PostLikesListDAORepository;
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
    private final PostLikesListDAORepository postLikesListDAORepository;
    private final FavoritesDAORepository favoritesDAORepository;

    // Get Post by ID
    @GetMapping("/{userId}/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        //fetch join으로 모든 정보 한번에 조회
        Optional<Post> findPost = postDAORepository.findPostId(postId);
        if (findPost.isEmpty()) {
            throw new ResourceNotFoundException("Post not found with id " + postId);
        }

        Post post = findPost.get();
        //PostDTO로 변환
        PostDTO postDTO = PostDTO.of(post);


        //좋아요 여부
        boolean isLike = post.getPostLikesLists().stream().anyMatch(postLikesList -> postLikesList.getUser().getId().equals(userId));
        postDTO.setIsLike(isLike);

        //찜 여부
        boolean isFavorite = post.getFavorites().stream().anyMatch(favorites -> favorites.getUser().getId().equals(userId));
        postDTO.setIsFavorite(isFavorite);

        return ResponseEntity.ok(postDTO);
    }

    // Get Posts by WriterId
    @GetMapping("/{userId}")
    public ResponseEntity<PostsDTO> getAllPostBy(@PathVariable("userId") Long userID) {
        List<Post> posts = postDAORepository.findAllByWriterId(userID);
        UserWriterDTO userWriterDTO = UserWriterDTO.of(posts.get(0).getWriter());

        List<PostItemDTO> postItemDTOS = posts.stream().map(PostItemDTO::of).collect(Collectors.toList());

        PostsDTO postsDTO = new PostsDTO(userWriterDTO, postItemDTOS);
        return ResponseEntity.ok(postsDTO);
    }

    // Create a new Post
    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postService.createPost(post);
        return ResponseEntity.ok(savedPost);
    }

    //Update a Post by ID
    @PutMapping("/put/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post postDetails) {
        Post updatedPost = postService.updatePost(postId, postDetails);
        return ResponseEntity.ok(updatedPost);
    }

    // Delete a Post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    //강의 찾기 api
    @GetMapping("/title/{postTitle}")
    public ResponseEntity<List<Post>> getPostsByTitle(@PathVariable String postTitle) {
        List<Post> posts = postService.getPostsByTitle(postTitle);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/lecture/{lectureName}")
    public ResponseEntity<List<Post>> getPostsByLectureName(@PathVariable String lectureName) {
        List<Post> posts = postService.getPostsByLectureName(lectureName);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/professor/{professorName}")
    public ResponseEntity<List<Post>> getPostsByProfessorName(@PathVariable String professorName) {
        List<Post> posts = postService.getPostsByProfessorName(professorName);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Post>> getPostsByMajor(@PathVariable String major) {
        List<Post> posts = postService.getPostsByMajor(major);
        return ResponseEntity.ok(posts);
    }
}
