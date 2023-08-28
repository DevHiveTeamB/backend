package com.devhive03.Controller.api;

import com.devhive03.Service.CommentsLikesListService;
import com.devhive03.Service.CommunityPostLikesListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "댓글 좋아요", description = "댓글 좋아요 API")
@RestController
@RequestMapping("/CommentsLikesList")
public class CommentsLikesListController {
    @Autowired
    private CommentsLikesListService commentsLikesListService;

    //좋아요 생성
    @Operation(summary = "좋아요 생성")
    @PostMapping("/post")
    public String createcommentsLikesList(@Parameter Long userID, @Parameter Long postID) {
        return commentsLikesListService.createcommentsLikesList(userID, postID);
    }

    //좋아요 삭제
    @Operation(summary = "좋아요 삭제")
    @DeleteMapping("/delete")
    public String deletecommentsLikesList(@Parameter Long userID, @Parameter Long postID) {
        return commentsLikesListService.deletecommentsLikesList(userID, postID);
    }
}
