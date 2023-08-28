package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Report.CommunityPostReportRequestDTO;
import com.devhive03.Model.DTO.Report.CommunityPostReportResponseDTO;
import com.devhive03.Service.CommunityPostReportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communityreport")
public class CommunityPostReportController {

    @Autowired
    private CommunityPostReportService communityPostReportService;

    @Operation(summary = "Get community reports by userId")
    @GetMapping("/user/get/{userId}")
    public ResponseEntity<List<CommunityPostReportResponseDTO>> getCommunityPostReportByuserId(@RequestParam Long userId) {
        return ResponseEntity.ok(communityPostReportService.getCommunityPostReportByuserId(userId));
    }

    @Operation(summary = "Create a community report")
    @PostMapping("/post")
    public String createCommunityPostReport(@RequestBody CommunityPostReportRequestDTO communityPostReportRequestDTO) {
        return communityPostReportService.createCommunityPostReport(communityPostReportRequestDTO);
    }

    @Operation(summary = "Delete a community report")
    @DeleteMapping("/delete/{reportID}")
    public String deleteCommunityPostReport(@RequestParam Long reportID) {
        return communityPostReportService.deleteCommunityPostReport(reportID);
    }
}
