package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "PostLikesList")
public class PostLikesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer postLikeID;


    @Column(nullable = false)
    private Integer likedPostID;

    @ManyToOne
    @JoinColumn(name = "like_post_user_id", nullable = false)
    private User likePostUserID;

    // Getters and Setters
}