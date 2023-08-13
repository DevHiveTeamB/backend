package com.devhive03.Model.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post_likes_list")
public class PostLikesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postlike_id", nullable = false)
    private Long likePostID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_post_user_id", nullable = false)
    private User user;

    // Getters and Setters
}