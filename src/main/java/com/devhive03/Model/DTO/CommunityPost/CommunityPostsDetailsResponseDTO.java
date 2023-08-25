package com.devhive03.Model.DTO.CommunityPost;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CommunityPostsDetailsResponseDTO {

    private Long communityPostID;
    private Writer writer;
    private String communityPostTitle;
    private String communityPostContent;
    private Timestamp communityPostDate;
    private List<Picture> communityPostsPictures;  // Community Posts' pictures 정보를 추가
    private Long communityPostLikesCount;
    private Boolean isCommunityPostLikes;
    private List<Comment> comments;  // Comment 정보를 추가

    @Data
    public static class Writer {
        private Long id;
        private String username;
        private String loginId;
    }

    @Data
    public static class Picture {
        private Long communityPostsPictureID;
        private String picture;
    }

    @Data
    public static class Comment {
        private Long commentsID;
        private User user;
        private String commentContent;
        private Timestamp commentDate;

        @Data
        public static class User {
            private Long id;
            private String username;
            private String loginId;
        }
    }
}

