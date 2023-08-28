package com.devhive03.Controller.api;

import com.devhive03.Service.CommunityPostLikesListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "커뮤니티 좋아요", description = "커뮤니티 좋아요 API")
@RestController
@RequestMapping("/CommunityPostLikesList")
public class CommunityPostLikesListController {

    @Autowired
    private CommunityPostLikesListService communityPostLikesListService;

    //좋아요 생성
    @Operation(summary = "좋아요 생성")
    @PostMapping("/post")
    public String createCommunityPostLikesList(@Parameter Long userID, @Parameter Long postID) {
        return communityPostLikesListService.createCommunityPostLikesList(userID, postID);
    }

    //좋아요 삭제
    @Operation(summary = "좋아요 삭제")
    @DeleteMapping("/delete")
    public String deleteCommunityPostLikesList(@Parameter Long userID, @Parameter Long postID) {
        return communityPostLikesListService.deleteCommunityPostLikesList(userID, postID);
    }
}
