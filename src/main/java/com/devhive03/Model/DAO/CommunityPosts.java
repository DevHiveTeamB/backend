package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "communityposts")
public class CommunityPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communitypost_id", nullable = false)
    private Integer communityPostID;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(name = "communitypost_title")
    private String communityPostTitle;

    @Lob
    @Column(name = "communitypost_content")
    private String communityPostContent;

    @Column(name = "communitypost_date")
    private Timestamp communityPostDate;

    @Column(name = "communitypost_likes")
    private Integer communityPostLikes;

    //커뮤니티 글 사진 연관관계
    @OneToMany(mappedBy = "communityposts")
    private List<CommunityPostspicture> communityPostspictures = new ArrayList<>();

    //커뮤니티 글 신고 연관관계
    @OneToMany(mappedBy = "communityposts")
    private List<CommunityPostReport> communityPostReports = new ArrayList<>();

    //커뮤니티 글 좋아요 목록 연관관계
    @OneToMany(mappedBy = "communityposts")
    private List<CommunityPostLikesList> communityPostLikesLists = new ArrayList<>();

    //커뮤니티 글 댓글 연관관계
    @OneToMany(mappedBy = "communityposts")
    private List<Comments> comments = new ArrayList<>();

    // Getters and Setters
}