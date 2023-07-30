package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "Favorites")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer favoriteID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userID;

    @Column(nullable = false)
    private Integer postID;

    private Timestamp favoriteDate;

    // Getters and Setters
}