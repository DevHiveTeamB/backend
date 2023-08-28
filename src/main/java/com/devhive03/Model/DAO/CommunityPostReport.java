package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "communitypost_reports")
public class CommunityPostReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Report_id", nullable = false)
    private Long reportID;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Communitypost_id", nullable = false)
    private CommunityPosts communityPost;

    @Column(name = "ReportContent")
    private String reportContent = "";

    @Column(name = "ReportDate")
    private Timestamp reportDate = new java.sql.Timestamp(System.currentTimeMillis());

    // Getters and Setters
}