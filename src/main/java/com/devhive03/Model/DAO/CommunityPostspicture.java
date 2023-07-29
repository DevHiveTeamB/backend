package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "CommunityPostspicture")
public class CommunityPostspicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer communityPostspictureID;

    @Column(nullable = false)
    private Integer communityPostID;

    @Column(nullable = false)
    private Integer writerID;

    @Column
    private String picture;

    // Getters and Setters
}