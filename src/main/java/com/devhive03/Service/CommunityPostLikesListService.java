package com.devhive03.Service;

import com.devhive03.Model.DAO.*;
import com.devhive03.Repository.CommunityPostLikesListDAORepository;
import com.devhive03.Repository.CommunityPostsDAORepository;
import com.devhive03.Repository.PostDAORepository;
import com.devhive03.Repository.UserDAORepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommunityPostLikesListService {
    @Autowired
    private CommunityPostLikesListDAORepository communityPostLikesListDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private CommunityPostsDAORepository communityPostsDAORepository;

    @Transactional
    public String createCommunityPostLikesList(Long userID, Long postID){
        Optional<User> user = userDAORepository.findById(userID);
        Optional<CommunityPosts> post = communityPostsDAORepository.findById(postID);

        CommunityPostLikesList communityPostLikesList = new CommunityPostLikesList();
        communityPostLikesList.setUser(user.get());
        communityPostLikesList.setLikedCommunityPost(post.get());

        communityPostLikesListDAORepository.save(communityPostLikesList);
        return "{\"message\":\"Favorites created successfully\"}";
    }

    @Transactional
    public String deleteCommunityPostLikesList(Long userID, Long postID){
        communityPostLikesListDAORepository.deleteByUser_IdAndLikedCommunityPost_CommunityPostID(userID, postID);
        return "{\"message\":\"Favorites deleted successfully\"}";
    }
}
