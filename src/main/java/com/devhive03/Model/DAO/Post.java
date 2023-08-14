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
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
        lecture.getPosts().add(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Lob
    @Column(name = "post_content", nullable = false)
    private String postContent;

    @Column(name = "post_date", nullable = false)
    private Timestamp postDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "hits")
    private Integer hits = 0;

    //쪽지방 연관관계
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "message_room_id")
    private MessageRoom messageRoom;

    //게시글 사진 연관관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostPicture> postPictures = new ArrayList<>();

    //게시글 신고 연관관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostReports> postReports = new ArrayList<>();

    //계시글 좋아요 목록 연관관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLikesList> postLikesLists = new ArrayList<>();

    //게시글 찜 연관관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorites> favorites = new ArrayList<>();
    // Getters and Setters
}