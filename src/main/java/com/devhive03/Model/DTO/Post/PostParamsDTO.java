package com.devhive03.Model.DTO.Post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class PostParamsDTO {
    private Long userId = null;
    private String postTitle = null;
    private String professor = null;
    private String lectureName = null;
    private String major = null;
}
