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

    @Column(nullable = false)
    private Integer likePostUserID;

    // Getters and Setters
}