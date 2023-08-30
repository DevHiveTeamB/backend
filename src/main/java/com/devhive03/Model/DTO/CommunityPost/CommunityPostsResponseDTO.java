package com.devhive03.Model.DTO.CommunityPost;

import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.User;
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
    private Long commentcount;

    @Data
    public static class Writer {
        private Long id;
        private String username;
        private String loginId;
        private String profilePhoto;
    }

}