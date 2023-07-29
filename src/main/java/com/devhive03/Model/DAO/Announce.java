package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Announce")
public class Announce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer announceID;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    // Getters and Setters
}