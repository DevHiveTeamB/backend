package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.Post;
import com.devhive03.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Get Post by ID
    @GetMapping("/get/{postId}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Long postId) {
        Optional<Post> post = postService.getPost(postId);
        return ResponseEntity.ok(post);
    }

    // Get Posts by User ID
    @GetMapping("/user/get/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
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
