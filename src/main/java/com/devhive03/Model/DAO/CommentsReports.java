package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Comments_reports")
public class CommentsReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Integer reportID;

    @ManyToOne
    @JoinColumn(name = "comments_id", nullable = false)
    private Comments comments;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id", nullable = false)
    private User raporterUserID;

    @Column(name = "report_content")
    private String reportContent;

    @Column(name = "report_date")
    private Timestamp reportDate;

    // Getters and Setters
}