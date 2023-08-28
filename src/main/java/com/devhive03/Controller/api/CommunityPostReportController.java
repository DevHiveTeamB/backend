package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Report.CommunityPostReportRequestDTO;
import com.devhive03.Model.DTO.Report.CommunityPostReportResponseDTO;
import com.devhive03.Service.CommunityPostReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "커뮤니티글 신고", description = "커뮤니티글 신고 API")
@RestController
@RequestMapping("/communityreport")
public class CommunityPostReportController {

    @Autowired
    private CommunityPostReportService communityPostReportService;

    @Operation(summary = "userId로 조회")
    @GetMapping("/user/get/{userId}")
    public ResponseEntity<List<CommunityPostReportResponseDTO>> getCommunityPostReportByuserId(@RequestParam Long userId) {
        return ResponseEntity.ok(communityPostReportService.getCommunityPostReportByuserId(userId));
    }

    @Operation(summary = "신고 생성")
    @PostMapping("/post")
    public String createCommunityPostReport(@RequestBody CommunityPostReportRequestDTO communityPostReportRequestDTO) {
        return communityPostReportService.createCommunityPostReport(communityPostReportRequestDTO);
    }

    @Operation(summary = "신고 삭제")
    @DeleteMapping("/delete/{reportID}")
    public String deleteCommunityPostReport(@RequestParam Long reportID) {
        return communityPostReportService.deleteCommunityPostReport(reportID);
    }
}
