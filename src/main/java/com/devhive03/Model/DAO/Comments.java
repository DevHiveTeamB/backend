package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Integer commentsID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "communitypost_id", nullable = false)
    private CommunityPosts communityPosts;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_date")
    private Timestamp commentDate;

    //댓글 좋아요 목록 연관관계
    @OneToMany(mappedBy = "comments")
    private List<CommentsLikesList> commentsLikesLists = new ArrayList<>();

    //댓글 신고 연관관계
    @OneToMany(mappedBy = "comments")
    private List<CommentsReports> commentsReports = new ArrayList<>();


    // Getters and Setters
}