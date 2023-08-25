package com.devhive03.Service;

import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsRequestDTO;
import com.devhive03.Repository.CommunityPostsDAORepository;
import com.devhive03.Repository.UserDAORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityPostsService {

    private final CommunityPostsDAORepository communityPostsDAORepository;
    private final UserDAORepository userDAORepository;


    public CommunityPosts createCommunityPost(CommunityPostsRequestDTO communityPostRequest) throws Exception {
        CommunityPosts communityPost = new CommunityPosts();

        // Convert DTO to Entity
        Optional<User> writerOpt = userDAORepository.findById(communityPostRequest.getWriterID());

        if (!writerOpt.isPresent()) {
            throw new Exception("User not found with provided writerID");
        }

        communityPost.setWriter(writerOpt.get());
        communityPost.setCommunityPostTitle(communityPostRequest.getCommunityPostTitle());
        communityPost.setCommunityPostContent(communityPostRequest.getCommunityPostContent());

        try {
            return communityPostsDAORepository.save(communityPost);
        } catch (Exception e) {
            throw new Exception("Error occurred while saving the CommunityPost", e);
        }
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
    public CommunityPosts updateCommunityPost(Long communityPostID, CommunityPostsRequestDTO communityPostRequest) {
        return communityPostsDAORepository.findById(communityPostID)
                .map(communityPost -> {
                    communityPost.setCommunityPostTitle(communityPostRequest.getCommunityPostTitle());
                    communityPost.setCommunityPostContent(communityPostRequest.getCommunityPostContent());
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
