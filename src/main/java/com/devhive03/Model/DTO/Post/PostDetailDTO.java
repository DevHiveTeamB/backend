package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DAO.Lecture;
import lombok.*;

import java.sql.Timestamp;
@Data
@Builder
@Setter @Getter
@AllArgsConstructor
public class PostDetailDTO {
    private Long postID;
    private String postTitle;
    private Timestamp postDate;
    private Integer price;
    private Integer hits;
    private Lecture lecture;
    // getters and setters
}