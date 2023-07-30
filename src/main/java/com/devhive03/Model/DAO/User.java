package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "login_id", nullable = false, unique = true, length = 50)
    private String loginId;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "profile_photo", columnDefinition = "TEXT")
    private String profilePhoto;

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "membership")
    private Byte membership;

    @Column(name = "certification")
    private Byte certification;

    @Column(name = "rating_state")
    private Byte ratingState;

    //게시글 연관관계
    @OneToMany(mappedBy = "users")
    private List<Post> posts = new ArrayList<>();

    //쪽지방 연관관계
    @OneToMany(mappedBy = "users")
    private List<MessageRoom> messageRooms = new ArrayList<>();

    //쪽지방 신고 연관관계
    @OneToMany(mappedBy = "users")
    private List<MessageRoomsReport> messageRoomsReports = new ArrayList<>();

    //게시글 좋아요 목록 연관관계
    @OneToMany(mappedBy = "users")
    private List<PostLikesList> postLikesLists = new ArrayList<>();

    //게시글 찜 목록 연관관계
    @OneToMany(mappedBy = "users")
    private List<Favorites> favorites = new ArrayList<>();

    //게시글 신고 연관관계
    @OneToMany(mappedBy = "users")
    private List<PostReports> postReports = new ArrayList<>();

    //최근검색 목록 연관관계
    @OneToMany(mappedBy = "users")
    private List<SearchList> searchLists = new ArrayList<>();

    //커뮤니티 좋아요 목록 연관관계
    @OneToMany(mappedBy = "users")
    private List<CommunityPostLikesList> communityPostLikesLists = new ArrayList<>();

    //커뮤니티 게시글 연관관계
    @OneToMany(mappedBy = "users")
    private List<CommunityPosts> communityPosts = new ArrayList<>();

    //커뮤니티 게시글 신고 연관관계
    @OneToMany(mappedBy = "users")
    private List<CommunityPostReport> communityPostReports = new ArrayList<>();

    //댓글 연관관계
    @OneToMany(mappedBy = "users")
    private List<Comments> comments = new ArrayList<>();

    //댓글 좋아요 목록 연관관계
    @OneToMany(mappedBy = "users")
    private List<CommentsLikesList> commentsLikesLists = new ArrayList<>();

    //댓글 신고 목록 연관관계
    @OneToMany(mappedBy = "users")
    private List<CommentsReports> commentsReports = new ArrayList<>();
    // getters and setters

    // You can add @PrePersist and @PreUpdate methods if you want to automatically hash the password, for example.
}