package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DAO.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter @Getter
public class PostUpdateDTO{
    private Long postId;
    private Long lectureId;
    private String postTitle;
    private String postContent;
    private Integer price;
}
