package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DTO.Lecture.LectureDTO;
import com.devhive03.Model.DTO.User.UserWriterDTO;
import lombok.*;

@Data
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostItemDTO {
    private Long postId;
    private String postTitle;
    private String postContent;
    private String postDate;
    private Integer price;
    private Integer hits;
    private String picture;
    private UserWriterDTO writer;
    private LectureDTO lecture;

    public static PostItemDTO of(Post post) {
        String picture = post.getPostPictures().isEmpty() ? null : post.getPostPictures().get(0).getPicture();

        return PostItemDTO.builder()
                .postId(post.getPostId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postDate(post.getPostDate().toString())
                .price(post.getPrice())
                .hits(post.getHits())
                .picture(picture)
                .writer(UserWriterDTO.of(post.getWriter()))
                .lecture(LectureDTO.of(post.getLecture()))
                .build();
    }
}
