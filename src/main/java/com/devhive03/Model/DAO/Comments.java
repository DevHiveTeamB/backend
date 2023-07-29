package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer commentsID;

    @Column(nullable = false)
    private Integer userID;

    @Column(nullable = false)
    private Integer communityPostID;

    @Column
    private String commentContent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;

    @Column
    private Integer commentLikes;

    // Getters and Setters
}