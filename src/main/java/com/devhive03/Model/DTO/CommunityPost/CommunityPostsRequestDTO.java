package com.devhive03.Model.DTO.CommunityPost;

import lombok.Data;

@Data
public class CommunityPostsRequestDTO {

    private Long writerID;
    private String communityPostTitle;
    private String communityPostContent;
    private Long communityPostLikes;
}