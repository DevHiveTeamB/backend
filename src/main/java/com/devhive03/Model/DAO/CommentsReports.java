package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CommentsReports")
public class CommentsReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer reportID;

    @Column(nullable = false)
    private Integer commentsID;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id", nullable = false)
    private User raporterUserID;

    @Column
    private String reportContent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;

    // Getters and Setters
}