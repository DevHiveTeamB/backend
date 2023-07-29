package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "CommunityPostLikesList")
public class CommunityPostLikesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer likeCommunityPostID;

    @Column(nullable = false)
    private Integer likeUserID;

    @Column(nullable = false)
    private Integer likedCommunityPost;

    // Getters and Setters
}