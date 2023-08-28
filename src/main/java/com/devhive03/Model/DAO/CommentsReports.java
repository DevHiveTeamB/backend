package com.devhive03.Model.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "comments_reports")
public class CommentsReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long reportID;

    @ManyToOne
    @JoinColumn(name = "comments_id", nullable = false)
    private Comments comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "report_content")
    private String reportContent = "";

    @Column(name = "report_date")
    private Timestamp reportDate = new java.sql.Timestamp(System.currentTimeMillis());

    // Getters and Setters
}