package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "CommentsLikesList")
public class CommentsLikesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer favoriteCommentsID;

    @Column(nullable = false)
    private Integer commentsID;

    @Column(nullable = false)
    private Integer userID;

    @Column
    private Integer favoriteCommentsLikes;

    // Getters and Setters
}