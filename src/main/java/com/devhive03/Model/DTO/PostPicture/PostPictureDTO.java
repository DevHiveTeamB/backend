package com.devhive03.Model.DTO.PostPicture;

import com.devhive03.Model.DAO.PostPicture;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PostPictureDTO {
    private String picture;

    public static PostPictureDTO of(PostPicture postPicture) {
        return PostPictureDTO.builder()
            .picture(postPicture.getPicture())
            .build();
    }

    public static List<PostPictureDTO> ofList(List<PostPicture> postPictures) {
        return postPictures.stream().map(PostPictureDTO::of).collect(Collectors.toList());
    }
}
