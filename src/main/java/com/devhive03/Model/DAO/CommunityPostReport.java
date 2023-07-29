package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "CommunityPostReports")
public class CommunityPostReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReportID", nullable = false)
    private int reportID;

    @Column(name = "ReporterUserID", nullable = false)
    private int reporterUserID;

    @Column(name = "CommunityPostID", nullable = false)
    private int communityPostID;

    @Column(name = "ReportContent")
    private String reportContent;

    @Column(name = "ReportDate")
    private Timestamp reportDate;

    // Getters and Setters
}