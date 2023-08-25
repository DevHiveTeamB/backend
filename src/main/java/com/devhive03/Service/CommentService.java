package com.devhive03.Service;

import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DTO.Comment.CommentDTO;
import com.devhive03.Repository.CommentsDAORepository;
import com.devhive03.Repository.CommunityPostsDAORepository;
import com.devhive03.Repository.UserDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentsDAORepository commentsDAORepository;
    @Autowired
    private CommunityPostsDAORepository communityPostsDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;

    public Comments createComment(CommentDTO commentDTO) {
        Comments comments = new Comments();
        comments.setUser(userDAORepository.findById(commentDTO.getUserID()).get());
        comments.setCommunityPosts(communityPostsDAORepository.findById(commentDTO.getCommunityPostID()).get());
        comments.setCommentContent(commentDTO.getCommentContent());
        return commentsDAORepository.save(comments);

    }
}
