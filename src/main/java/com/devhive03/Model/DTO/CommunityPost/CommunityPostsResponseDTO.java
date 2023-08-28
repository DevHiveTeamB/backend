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
    private Boolean isCommunityPostLikes;
    private Long commentcount;

    @Data
    public static class Writer {
        private Long id;
        private String username;
        private String loginId;

        public static Writer of(User user){
            Writer writer = new Writer();
            writer.setId(user.getId());
            writer.setUsername(user.getUsername());
            writer.setLoginId(user.getLoginId());
            return writer;
        }
    }

    public static CommunityPostsResponseDTO of(CommunityPosts communityPosts){
        CommunityPostsResponseDTO communityPostsResponseDTO = new CommunityPostsResponseDTO();
        communityPostsResponseDTO.setCommunityPostID(communityPosts.getCommunityPostID());
        communityPostsResponseDTO.setWriter(Writer.of(communityPosts.getWriter()));
        communityPostsResponseDTO.setCommunityPostTitle(communityPosts.getCommunityPostTitle());
        communityPostsResponseDTO.setCommunityPostContent(communityPosts.getCommunityPostContent());
        communityPostsResponseDTO.setCommunityPostDate(communityPosts.getCommunityPostDate());
        communityPostsResponseDTO.setCommunityPostLikesCount(communityPosts.getCommunityPostLikes());
        communityPostsResponseDTO.setIsCommunityPostLikes(communityPosts.getIsCommunityPostLikes());
        return communityPostsResponseDTO;
    }
}