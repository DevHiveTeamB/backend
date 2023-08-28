package com.devhive03.Service;

import com.devhive03.Model.DAO.*;
import com.devhive03.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentsLikesListService {
    @Autowired
    private CommentsLikesListDAORepository commentsLikesListDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private CommentsDAORepository commentsDAORepository;

    @Transactional
    public String createcommentsLikesList(Long userID, Long postID){
        Optional<User> user = userDAORepository.findById(userID);
        Optional<Comments> post = commentsDAORepository.findById(postID);

        CommentsLikesList communityPostLikesList = new CommentsLikesList();
        communityPostLikesList.setUser(user.get());
        communityPostLikesList.setComments(post.get());

        commentsLikesListDAORepository.save(communityPostLikesList);
        return "{\"message\":\"Favorites created successfully\"}";
    }

    @Transactional
    public String deletecommentsLikesList(Long userID, Long postID){
        commentsLikesListDAORepository.deleteByUser_IdAndComments_CommentsID(userID, postID);
        return "{\"message\":\"Favorites deleted successfully\"}";
    }
}
