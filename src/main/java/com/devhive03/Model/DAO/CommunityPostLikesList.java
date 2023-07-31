package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "communitypost_likes_list")
public class CommunityPostLikesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long likeCommunityPostID;

    @ManyToOne
    @JoinColumn(name = "like_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "liked_communitypost", nullable = false)
    private CommunityPosts likedCommunityPost;

    // Getters and Setters
}