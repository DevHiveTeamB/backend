package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer postID;

    @Column(name = "lecture_id", nullable = false)
    private Integer lectureID;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Lob
    @Column(name = "post_content", nullable = false)
    private String postContent;

    @Column(name = "post_date", nullable = false)
    private Timestamp postDate;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "hits")
    private Integer hits;

    // Getters and Setters
}