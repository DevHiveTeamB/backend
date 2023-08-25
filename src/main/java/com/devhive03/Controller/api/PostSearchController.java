package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Post.PostDetailDTO;
import com.devhive03.Service.PostSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Tag(name = "게시물 검색", description = "게시글 검색 API")
@RestController
@RequestMapping("/lectures")
public class PostSearchController {
    @Autowired
    private PostSearchService postSearchService;

    @Operation(summary = "게시글먕으로 검색")
    @GetMapping("/title/get/{postTitle}")
    public List<PostDetailDTO> getPostsByPostTitle(@PathVariable String postTitle) {
        return Collections.unmodifiableList(postSearchService.getPostsByPostTitle(postTitle));
    }


    @Operation(summary = "강의명으로 조회")
    @GetMapping("/course/get/{lectureName}")
    public List<PostDetailDTO> getPostsBy_LectureName(@PathVariable String lectureName) {
        return Collections.unmodifiableList(postSearchService.getPostsByLectureName(lectureName));
    }


    @Operation(summary = "교수명으로 조회")
    @GetMapping("/professor/get/{professorName}")
    public List<PostDetailDTO> getPostsByProfessorName(@PathVariable String professorName) {
        return Collections.unmodifiableList(postSearchService.getPostsByProfessorName(professorName));
    }


    @Operation(summary = "전공명으로 조회")
    @GetMapping("/major/get/{major}")
    public List<PostDetailDTO> getPostsByMajor(@PathVariable String major) {
        return Collections.unmodifiableList(postSearchService.getPostsByMajor(major));
    }
}
