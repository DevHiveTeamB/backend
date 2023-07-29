package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer postID;

    @Column(nullable = false)
    private Integer lectureID;

    @Column(nullable = false)
    private Integer writerID;

    @Column
    private String postTitle;

    @Lob
    @Column
    private String postContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date postDate;

    @Column
    private Integer price;

    @Column
    private Integer hits;

    // Getters and Setters
}