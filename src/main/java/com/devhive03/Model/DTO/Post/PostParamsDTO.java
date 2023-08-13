package com.devhive03.Model.DTO.Post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class PostParamsDTO {
    private String postTitle = "";
    private String lectureName = "";
    private String major = "";
    private String professor = "";
}
