package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "post_reports")
public class PostReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Integer reportID;

    @ManyToOne
    @JoinColumn(name= "report_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "report_content")
    private String reportContent;

    @Column(name = "report_date")
    private Timestamp reportDate;

    // Getters and Setters
}