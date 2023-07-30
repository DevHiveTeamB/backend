package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "CommunityPostLikesList")
public class CommunityPostLikesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer likeCommunityPostID;

    @ManyToOne
    @JoinColumn(name = "like_user_id", nullable = false)
    private User likeUserID;

    @Column(nullable = false)
    private Integer likedCommunityPost;

    // Getters and Setters
}