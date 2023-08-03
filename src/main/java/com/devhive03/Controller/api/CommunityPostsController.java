package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Service.CommunityPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/communityposts")
@RequiredArgsConstructor
public class CommunityPostsController {

    private final CommunityPostsService communityPostsService;

    // Get Post by ID
    @GetMapping("/get/{communityPostID}")
    public ResponseEntity<Optional<CommunityPosts>> getCommunityPostById(@PathVariable Long communityPostID) {
        Optional<CommunityPosts> communityPost = communityPostsService.getCommunityPost(communityPostID);
        return ResponseEntity.ok(communityPost);
    }

    // Get Posts by User ID
    @GetMapping("/user/get/{userId}")
    public ResponseEntity<List<CommunityPosts>> getCommunityPostsByUserId(@PathVariable Long userId) {
        List<CommunityPosts> communityPosts = communityPostsService.getCommunityPostsByUser(userId);
        return ResponseEntity.ok(communityPosts);
    }

    // Create a new Post
    @PostMapping("/post")
    public ResponseEntity<CommunityPosts> createCommunityPost(@RequestBody CommunityPosts communityPost) {
        CommunityPosts savedCommunityPost = communityPostsService.createCommunityPost(communityPost);
        return ResponseEntity.ok(savedCommunityPost);
    }

    //Update a Post by ID
    @PutMapping("/put/{communityPostID}")
    public ResponseEntity<CommunityPosts> updateCommunityPost(@PathVariable Long communityPostID, @RequestBody CommunityPosts communityPostDetails) {
        CommunityPosts updatedCommunityPost = communityPostsService.updateCommunityPost(communityPostID, communityPostDetails);
        return ResponseEntity.ok(updatedCommunityPost);
    }

    // Delete a Post
    @DeleteMapping("/delete/{communityPostID}")
    public ResponseEntity<?> deleteCommunityPost(@PathVariable Long communityPostID) {
        communityPostsService.deleteCommunityPost(communityPostID);
        return ResponseEntity.ok().build();
    }
}
