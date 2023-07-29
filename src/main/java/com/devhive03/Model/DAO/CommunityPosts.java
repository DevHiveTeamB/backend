package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "CommunityPosts")
public class CommunityPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer communityPostID;

    @Column(nullable = false)
    private Integer writerID;

    @Column
    private String communityPostTitle;

    @Column
    private String communityPostContent;

    @Column
    private Timestamp communityPostDate;

    @Column
    private Integer communityPostLikes;

    // Getters and Setters
}