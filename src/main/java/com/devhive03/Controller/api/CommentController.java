package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DTO.Comment.CommentDTO;
import com.devhive03.Repository.CommentsDAORepository;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "댓글", description = "댓글 API")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "댓글 작성")
    @PostMapping("/post")
    public String createCommentPost(@RequestBody CommentDTO comment) {
        Comments savedComment = commentService.createComment(comment);
        return "{\"message\":\"success\"}";
    }

}
    
