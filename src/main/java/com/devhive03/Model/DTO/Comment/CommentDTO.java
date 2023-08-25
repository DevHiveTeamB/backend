package com.devhive03.Model.DTO.Comment;

import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDTO {
    private Long userID;
    private Long communityPostID;
    private String commentContent;
}
