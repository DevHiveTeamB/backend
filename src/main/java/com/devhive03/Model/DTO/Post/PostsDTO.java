package com.devhive03.Model.DTO.Post;

import com.devhive03.Model.DTO.User.UserWriterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter @Setter
@AllArgsConstructor
public class PostsDTO {
    private UserWriterDTO writer;
    private List<PostItemDTO> posts = new ArrayList<>();
}
