package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DTO.Lecture.LectureDTO;
import com.devhive03.Model.DTO.PostPicture.PostPictureDTO;
import com.devhive03.Model.DTO.User.UserDTO;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@Data
public class PostDTO {
    private Long postId;
    private LectureDTO lecture;
    private UserDTO writer;
    private String postTitle;
    private String postContent;
    private Timestamp postDate;
    private Integer price;
    private Integer hits;
    private List<PostPictureDTO> postPictures;
    //favorite 수
    private Integer favorite;
    private Boolean isFavorite;

    public static PostDTO of(Post post) {
        return PostDTO.builder()
                .postId(post.getPostId())
                .lecture(LectureDTO.of(post.getLecture()))
                .writer(UserDTO.of(post.getWriter()))
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postDate(post.getPostDate())
                .price(post.getPrice())
                .hits(post.getHits())
                .postPictures(PostPictureDTO.ofList(post.getPostPictures()))
                .build();
    }
}
