package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "Communitypost_reports")
public class CommunityPostReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Report_id", nullable = false)
    private int reportID;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id", nullable = false)
    private User reporterUser;

    @ManyToOne
    @JoinColumn(name = "Communitypost_id", nullable = false)
    private CommunityPosts communityPost;

    @Column(name = "ReportContent")
    private String reportContent;

    @Column(name = "ReportDate")
    private Timestamp reportDate;

    // Getters and Setters
}