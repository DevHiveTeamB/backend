package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Report.CommentsReportRequestDTO;
import com.devhive03.Model.DTO.Report.CommentsReportResponseDTO;
import com.devhive03.Service.CommentsReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글 신고", description = "댓글 신고 API")
@RestController
@RequestMapping("/commentsreports")
public class CommentsReportController {

    @Autowired
    private CommentsReportService commentsReportService;

    @Operation(summary = "userId로 신고 조회")
    @GetMapping("/user/get/{userID}")
    public ResponseEntity<List<CommentsReportResponseDTO>> getReportByUserId(@Parameter Long userId) {
        return ResponseEntity.ok(commentsReportService.getReportByUserId(userId));
    }

    @Operation(summary = "신고 생성")
    @PostMapping("/post")
    public String createCommentReport(@RequestBody CommentsReportRequestDTO commentsReportRequestDTO) {
        return commentsReportService.createCommentReport(commentsReportRequestDTO);
    }

    @Operation(summary = "신고 삭제")
    @DeleteMapping("/delete/{reportID}")
    public String deleteCommentReport(@PathVariable Long reportID) {
        return commentsReportService.deleteCommentReport(reportID);
    }
}
