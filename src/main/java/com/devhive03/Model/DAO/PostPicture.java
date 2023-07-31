package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "posts_picture")
public class PostPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_picture_id", nullable = false)
    private Integer postspictureID;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    @Column(name = "picture")
    private String picture;

    // Getters and Setters
}