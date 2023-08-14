package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DTO.User.UserWriterDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostsDTO {
    private UserWriterDTO writer;
    private List<PostItemDTO> posts = new ArrayList<>();
}
