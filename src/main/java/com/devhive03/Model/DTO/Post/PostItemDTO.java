package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DAO.Post;
import lombok.*;

@Data
@Builder
@Getter @Setter
@AllArgsConstructor
public class PostItemDTO {
    private Long postID;
    private String postTitle;
    private String postContent;
    private String postDate;
    private Integer price;
    private Integer hits;
    private String picture;

    public static PostItemDTO of(Post post) {
        String picture = post.getPostPictures().isEmpty() ? null : post.getPostPictures().get(0).getPicture();

        return PostItemDTO.builder()
                .postID(post.getPostId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postDate(post.getPostDate().toString())
                .price(post.getPrice())
                .hits(post.getHits())
                .picture(picture)
                .build();
    }
}
