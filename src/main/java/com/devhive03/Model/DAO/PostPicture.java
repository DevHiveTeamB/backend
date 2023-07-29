package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "Postspicture")
public class PostPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer postspictureID;

    @Column(nullable = false)
    private Integer postID;

    @Column(nullable = false)
    private Integer userID;

    @Lob
    @Column
    private String picture;

    // Getters and Setters
}