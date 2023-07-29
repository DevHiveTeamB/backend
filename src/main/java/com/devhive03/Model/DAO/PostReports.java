package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "PostReports")
public class PostReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer reportID;

    @Column(nullable = false)
    private Integer reportUserID;

    @Column(nullable = false)
    private Integer postID;

    private String reportContent;

    private Timestamp reportDate;

    // Getters and Setters
}