package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Report.PostReportRequestDTO;
import com.devhive03.Model.DTO.Report.PostReportResponseDTO;
import com.devhive03.Service.PostReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postreport")
public class PostReportController {

    @Autowired
    private PostReportService postReportService;


    @Operation(summary = "userId로 신고 리스트 조회")
    @GetMapping("/user/get/{userId}")
    public ResponseEntity<List<PostReportResponseDTO>> getPostReportByuserId(@Parameter Long userId) {
        return ResponseEntity.ok(postReportService.getPostReportByuserId(userId));
    }

    // Create a new Post
    @Operation(summary = "게시글 신고 생성")
    @PostMapping("/post")
    public String createPostReport(@RequestBody PostReportRequestDTO postReportRequestDTO) {
        return postReportService.createPostReport(postReportRequestDTO);
    }

    // Delete a Post
    @Operation(summary = "게시글 신고 삭제")
    @DeleteMapping("/delete/{ReportID}")
    public String deletePostReport(@Parameter Long ReportID) {
        return postReportService.deletePostReport(ReportID);
    }
}
