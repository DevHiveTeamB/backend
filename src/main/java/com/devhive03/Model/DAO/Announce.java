package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "Announce")
public class Announce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announce_id", nullable = false)
    private Integer announceID;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Timestamp date;

    // Getters and Setters
}