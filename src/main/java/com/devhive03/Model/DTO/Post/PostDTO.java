package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DAO.Lecture;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.Lecture.LectureDTO;
import com.devhive03.Model.DTO.PostPicture.PostPictureDTO;
import com.devhive03.Model.DTO.User.UserWriterDTO;
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
    private UserWriterDTO writer;
    private String postTitle;
    private String postContent;
    private Timestamp postDate;
    private Integer price;
    private Integer hits;
    private List<PostPictureDTO> postPictures;
    private Boolean isLike;
    private Boolean isFavorite;

    public static PostDTO of(Post post) {
        return PostDTO.builder()
                .postId(post.getPostId())
                .lecture(LectureDTO.of(post.getLecture()))
                .writer(UserWriterDTO.of(post.getWriter()))
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postDate(post.getPostDate())
                .price(post.getPrice())
                .hits(post.getHits())
                .postPictures(PostPictureDTO.ofList(post.getPostPictures()))
                .build();
    }
}
