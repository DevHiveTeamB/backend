package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Report.CommentsReportRequestDTO;
import com.devhive03.Model.DTO.Report.CommentsReportResponseDTO;
import com.devhive03.Service.CommentsReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentsreports")
public class CommentsReportController {

    @Autowired
    private CommentsReportService commentsReportService;

    @Operation(summary = "Get reports by userId for comments")
    @GetMapping("/user/get/{userID}")
    public ResponseEntity<List<CommentsReportResponseDTO>> getReportByUserId(@Parameter Long userId) {
        return ResponseEntity.ok(commentsReportService.getReportByUserId(userId));
    }

    @Operation(summary = "Create a report for a comment")
    @PostMapping("/post")
    public String createCommentReport(@RequestBody CommentsReportRequestDTO commentsReportRequestDTO) {
        return commentsReportService.createCommentReport(commentsReportRequestDTO);
    }

    @Operation(summary = "Delete a report for a comment")
    @DeleteMapping("/delete/{reportID}")
    public String deleteCommentReport(@PathVariable Long reportID) {
        return commentsReportService.deleteCommentReport(reportID);
    }
}
