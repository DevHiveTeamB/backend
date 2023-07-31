package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "SearchList")
public class SearchList {

    @Id
    @Column(name = "search_data")
    private String searchData;

    @ManyToOne
    @JoinColumn(name = "search_user_id", nullable = false)
    private User user;

    @Column(name = "search_date")
    private Timestamp searchDate;

    // Getters and Setters
}