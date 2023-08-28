package com.devhive03.Model.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "comments_likes_list")
public class CommentsLikesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_comments_ic", nullable = false)
    private Long likeCommentsID;

    @ManyToOne
    @JoinColumn(name = "comments_id", nullable = false)
    private Comments comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    // Getters and Setters
}