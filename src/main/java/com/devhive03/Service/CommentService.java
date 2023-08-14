package com.devhive03.Service;

import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Repository.CommentsDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentsDAORepository commentsDAORepository;

    public Comments createComment(Comments comments) {
         return commentsDAORepository.save(comments);

    }
}
