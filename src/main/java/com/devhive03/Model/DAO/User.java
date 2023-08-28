package com.devhive03.Model.DAO;

import com.devhive03.Model.DTO.User.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", length = 50)
    private String username = "";

    @Column(name = "login_id", length = 50)
    private String loginId = "";

    @Column(name = "kakao_id", length = 50)
    private Long kakaoId = 0L;

    @Column(name = "password", length = 256)
    private String password = "";

    @Column(name = "email", length = 100)
    private String email = "";

    @Column(name = "phone_number", length = 15)
    private String phoneNumber = "";

    @Column(name = "profile_photo", columnDefinition = "TEXT")
    private String profilePhoto = "";

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction = "";

    @Column(name = "rating")
    private Long rating = 0L;

    @Column(name = "rating_user_cnt")
    private Long ratingUserCnt = 0L;


    //게시글 연관관계
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    //쪽지방 연관관계
    @OneToMany(mappedBy = "buyer")
    private List<MessageRoom> messageRooms = new ArrayList<>();

    //쪽지방 신고 연관관계
    @OneToMany(mappedBy = "user")
    private List<MessageRoomsReport> messageRoomsReports = new ArrayList<>();

    //게시글 좋아요 목록 연관관계
    @OneToMany(mappedBy = "user")
    private List<PostLikesList> postLikesLists = new ArrayList<>();

    //게시글 찜 목록 연관관계
    @OneToMany(mappedBy = "user")
    private List<Favorites> favorites = new ArrayList<>();

    //게시글 신고 연관관계
    @OneToMany(mappedBy = "user")
    private List<PostReports> postReports = new ArrayList<>();

    //최근검색 목록 연관관계
    @OneToMany(mappedBy = "user")
    private List<SearchList> searchLists = new ArrayList<>();

    //커뮤니티 좋아요 목록 연관관계
    @OneToMany(mappedBy = "user")
    private List<CommunityPostLikesList> communityPostLikesLists = new ArrayList<>();

    //커뮤니티 게시글 연관관계
    @OneToMany(mappedBy = "writer")
    private List<CommunityPosts> communityPosts = new ArrayList<>();

    //커뮤니티 게시글 신고 연관관계
    @OneToMany(mappedBy = "user")
    private List<CommunityPostReport> communityPostReports = new ArrayList<>();

    //댓글 연관관계
    @OneToMany(mappedBy = "user")
    private List<Comments> comments = new ArrayList<>();

    //댓글 좋아요 목록 연관관계
    @OneToMany(mappedBy = "user")
    private List<CommentsLikesList> commentsLikesLists = new ArrayList<>();

    //댓글 신고 목록 연관관계
    @OneToMany(mappedBy = "user")
    private List<CommentsReports> commentsReports = new ArrayList<>();

    //개인 쪽지 연관관계
    @OneToMany(mappedBy = "MessageWriter")
    private List<PrivateMessage> privateMessages = new ArrayList<>();

    //UserUpdateDTO를 User 삽입
    public User update(UserDTO userUpdateDTO) {
        this.username = userUpdateDTO.getUsername();
        this.phoneNumber = userUpdateDTO.getPhoneNumber();
        this.profilePhoto = userUpdateDTO.getProfilePhoto();
        this.introduction = userUpdateDTO.getIntroduction();
        this.rating = userUpdateDTO.getRatingState();
        return this;
    }



    // getters and setters

    // You can add @PrePersist and @PreUpdate methods if you want to automatically hash the password, for example.
}