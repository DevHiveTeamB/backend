package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "SearchList")
public class SearchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer searchData;

    @ManyToOne
    @JoinColumn(name = "search_user_id", nullable = false)
    private User searchUserID;

    private Timestamp searchDate;

    // Getters and Setters
}