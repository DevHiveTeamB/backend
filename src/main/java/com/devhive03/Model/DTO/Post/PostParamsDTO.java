package com.devhive03.Model.DTO.Post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class PostParamsDTO {
    private String postTitle = null;
    private String lectureName = null;
    private String major = null;
    private String professor = null;

    public void nullCheck(){
        //모두 null이면 ""으로 초기화
        if(postTitle == null && lectureName == null && major == null && professor == null){
            postTitle = "";
            lectureName = "";
            major = "";
            professor = "";
        }
    }
}
