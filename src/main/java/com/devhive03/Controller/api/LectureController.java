package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Lecture.LectureDTO;
import com.devhive03.Service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "강의 조회", description = "강의 조회 API")
@RestController
public class LectureController {
    @Autowired
    private LectureService lectureService;

    @Operation(summary = "강의명으로 조회")
    @GetMapping("/lectures/searchByLectureName")
    public List<LectureDTO> findByLectureName(@RequestParam String lectureName) {
        return lectureService.findByLectureName(lectureName);
    }

    @Operation(summary = "전공명으로 조회")
    @GetMapping("/lectures/searchByMajor")
    public List<LectureDTO> findByMajor(@RequestParam String major) {
        return lectureService.findByMajor(major);
    }

    @Operation(summary = "교수명으로 조회")
    @GetMapping("/lectures/searchByProfessor")
    public List<LectureDTO> findByProfessorName(@RequestParam String professor) {
        return lectureService.findByProfessorName(professor);
    }
}
