package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "login_id", nullable = false, unique = true, length = 50)
    private String loginId;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "profile_photo", columnDefinition = "TEXT")
    private String profilePhoto;

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "membership")
    private Byte membership;

    @Column(name = "certification")
    private Byte certification;

    @Column(name = "rating_state")
    private Byte ratingState;

    // getters and setters

    // You can add @PrePersist and @PreUpdate methods if you want to automatically hash the password, for example.
}