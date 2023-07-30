package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Integer commentsID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userID;

    @Column(nullable = false)
    private Integer communityPostID;

    @Column
    private String commentContent;

    private Timestamp commentDate;

    @Column
    private Integer commentLikes;

    // Getters and Setters
}