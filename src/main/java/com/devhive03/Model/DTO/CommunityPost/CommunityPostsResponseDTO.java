package com.devhive03.Model.DTO.CommunityPost;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CommunityPostsResponseDTO {

    private Long communityPostID;
    private Writer writer;
    private String communityPostTitle;
    private String communityPostContent;
    private Timestamp communityPostDate;
    private Long communityPostLikesCount;
    private Boolean isCommunityPostLikes;
    private List<Long> commentIDs;

    @Data
    public static class Writer {
        private Long id;
        private String username;
        private String loginId;
    }
}