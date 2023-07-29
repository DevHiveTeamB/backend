package com.devhive03.Model.DAO;

import jakarta.persistence.*;

@Entity
@Table(name = "Lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer lectureID;

    @Column
    private String lectureName;

    @Column
    private String professorName;

    @Column
    private String major;

    // Getters and Setters
}