package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "Communityposts_picture")
public class CommunityPostspicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communityposts_picture_id", nullable = false)
    private Integer communityPostspictureID;

    @ManyToOne
    @JoinColumn(name = "communitypost_id", nullable = false)
    private CommunityPosts communityPost;

    @Lob
    @Column(name = "picture")
    private String picture;

    // Getters and Setters
}