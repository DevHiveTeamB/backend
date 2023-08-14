package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DAO.Post;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class PostFormDTO {
    private Long userId;
    private Long lectureId;
    private String postTitle;
    private String postContent;
    private Integer price;

}
