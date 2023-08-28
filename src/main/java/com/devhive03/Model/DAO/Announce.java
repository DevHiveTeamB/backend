package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "announce")
public class Announce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announce_id", nullable = false)
    private Long announceID;

    @Column(name = "title")
    private String title = "";

    @Column(name = "content", columnDefinition = "TEXT")
    private String content = "";

    @Column(name = "data")
    private Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());

    // Getters and Setters
}