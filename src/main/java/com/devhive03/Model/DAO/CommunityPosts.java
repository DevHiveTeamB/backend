package com.devhive03.Model.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@Table(name = "communityposts")
public class CommunityPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communitypost_id", nullable = false)
    private Long communityPostID;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(name = "communitypost_title")
    private String communityPostTitle = "";

    @Lob
    @Column(name = "communitypost_content")
    private String communityPostContent = "";

    @Column(name = "communitypost_date")
    private Timestamp communityPostDate = new java.sql.Timestamp(System.currentTimeMillis());;

    //커뮤니티 글 신고 연관관계
    @OneToMany(mappedBy = "communityPost")
    private List<CommunityPostReport> communityPostReports = new ArrayList<>();

    //커뮤니티 글 좋아요 목록 연관관계
    @OneToMany(mappedBy = "likedCommunityPost")
    private List<CommunityPostLikesList> communityPostLikesLists = new ArrayList<>();

    //커뮤니티 글 댓글 연관관계
    @OneToMany(mappedBy = "communityPosts")
    private List<Comments> comments = new ArrayList<>();

    // Getters and Setters
}