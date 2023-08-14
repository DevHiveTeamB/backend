package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Repository.CommentsDAORepository;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/post/{CommentsID}")
    public ResponseEntity<Comments> createCommentPost(@RequestBody Comments comment) {
        Comments savedComment = commentService.createComment(comment);
        return ResponseEntity.ok(savedComment);
    }

}
    
