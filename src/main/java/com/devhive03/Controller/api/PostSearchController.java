package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Post.PostDetailDTO;
import com.devhive03.Service.PostSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/lectures")
public class PostSearchController {
    @Autowired
    private PostSearchService postSearchService;

    @GetMapping("/title/get/{postTitle}")
    public List<PostDetailDTO> getPostsByPostTitle(@PathVariable String postTitle) {
        //?
        return Collections.unmodifiableList(postSearchService.getPostsByPostTitle(postTitle));
    }
    @GetMapping("/course/get/{lectureName}")
    public List<PostDetailDTO> getPostsBy_LectureName(@PathVariable String lectureName) {
        //?
        return Collections.unmodifiableList(postSearchService.getPostsByLectureName(lectureName));
    }
    @GetMapping("/professor/get/{professorName}")
    public List<PostDetailDTO> getPostsByProfessorName(@PathVariable String professorName) {
        //?
        return Collections.unmodifiableList(postSearchService.getPostsByProfessorName(professorName));
    }
    @GetMapping("/major/get/{major}")
    public List<PostDetailDTO> getPostsByMajor(@PathVariable String major) {
        //?
        return Collections.unmodifiableList(postSearchService.getPostsByMajor(major));
    }
}
