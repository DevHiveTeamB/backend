package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id", nullable = false)
    private Long lectureID;

    @Column(name = "lecture_name", nullable = false, length = 50)
    private String lectureName;

    @Column(name = "professor_name", length = 50)
    private String professorName;

    @Column(name = "major", nullable = false, length = 50)
    private String major;

    //게시글 연관관계
    @OneToMany(mappedBy = "lecture")
    private List<Post> posts = new ArrayList<>();

    // Getters and Setters
}