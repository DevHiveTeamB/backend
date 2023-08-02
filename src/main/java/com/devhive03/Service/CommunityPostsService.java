package com.devhive03.Service;

import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Repository.CommunityPostsDAORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityPostsService {

    private final CommunityPostsDAORepository communityPostsDAORepository;

    // Create
    public CommunityPosts createCommunityPost(CommunityPosts communityPost) {
        return communityPostsDAORepository.save(communityPost);
    }

    // Read Single
    public Optional<CommunityPosts> getCommunityPost(Long id) {
        return communityPostsDAORepository.findById(id);
    }

    // Read by User ID
    public List<CommunityPosts> getCommunityPostsByUser(Long userId) {
        return communityPostsDAORepository.findAllByWriterId(userId);
    }

    // Read All
    public List<CommunityPosts> getAllCommunityPosts() {
        return communityPostsDAORepository.findAll();
    }

    // Update
    public CommunityPosts updateCommunityPost(Long communityPostID, CommunityPosts communityPostDetails) {
        return communityPostsDAORepository.findById(communityPostID)
                .map(communityPost -> {
                    communityPost.setCommunityPostTitle(communityPostDetails.getCommunityPostTitle());
                    communityPost.setCommunityPostContent(communityPostDetails.getCommunityPostContent());
                    // Add the rest of the post fields here
                    return communityPostsDAORepository.save(communityPost);
                })
                .orElseThrow(() -> new RuntimeException("CommunityPost with id " + communityPostID + " not found"));
    }

    // Delete
    public void deleteCommunityPost(Long id) {
        communityPostsDAORepository.deleteById(id);
    }
}
