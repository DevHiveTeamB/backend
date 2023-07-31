package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "post_likes_list")
public class PostLikesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postlike_id", nullable = false)
    private Integer likePostID;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "like_post_user_id", nullable = false)
    private User user;

    // Getters and Setters
}