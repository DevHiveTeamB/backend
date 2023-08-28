package com.devhive03.Service;

import com.devhive03.Model.DAO.CommunityPostReport;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DTO.Report.CommunityPostReportRequestDTO;
import com.devhive03.Model.DTO.Report.CommunityPostReportResponseDTO;
import com.devhive03.Repository.CommunityPostReportDAORepository;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Repository.CommunityPostsDAORepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityPostReportService {

    @Autowired
    private CommunityPostReportDAORepository communityPostReportDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private CommunityPostsDAORepository communityPostsDAORepository;

    @Transactional
    public List<CommunityPostReportResponseDTO> getCommunityPostReportByuserId(Long userID) {
        List<CommunityPostReport> communityPostReports = communityPostReportDAORepository.findAllByUserId(userID);

        return communityPostReports.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public String createCommunityPostReport(CommunityPostReportRequestDTO communityPostReportRequestDTO) {
        User user = userDAORepository.findById(communityPostReportRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommunityPosts communityPost = communityPostsDAORepository.findById(communityPostReportRequestDTO.getCommunityPostId())
                .orElseThrow(() -> new RuntimeException("Community Post not found"));

        CommunityPostReport communityPostReport = new CommunityPostReport();
        communityPostReport.setUser(user);
        communityPostReport.setCommunityPost(communityPost);
        communityPostReport.setReportContent(communityPostReportRequestDTO.getReportContent());

        communityPostReportDAORepository.save(communityPostReport);

        return "{\"message\":\"Community report created successfully\"}";
    }

    @Transactional
    public String deleteCommunityPostReport(Long reportID) {
        communityPostReportDAORepository.deleteById(reportID);
        return "{\"message\":\"Community report deleted successfully\"}";
    }

    private CommunityPostReportResponseDTO toDTO(CommunityPostReport communityPostReport) {
        CommunityPostReportResponseDTO dto = new CommunityPostReportResponseDTO();

        dto.setReportID(communityPostReport.getReportID());
        dto.setReportContent(communityPostReport.getReportContent());
        dto.setReportData(communityPostReport.getReportDate());

        CommunityPostReportResponseDTO.ReportCommunityPost reportCommunityPost = new CommunityPostReportResponseDTO.ReportCommunityPost();
        reportCommunityPost.setPostID(communityPostReport.getCommunityPost().getCommunityPostID());
        reportCommunityPost.setPostTitle(communityPostReport.getCommunityPost().getCommunityPostTitle());

        CommunityPostReportResponseDTO.ReportCommunityPost.CommunityReportedWriter reportedWriter = new CommunityPostReportResponseDTO.ReportCommunityPost.CommunityReportedWriter();
        reportedWriter.setWriterID(communityPostReport.getCommunityPost().getWriter().getId());
        reportedWriter.setWriterName(communityPostReport.getCommunityPost().getWriter().getUsername());
        reportedWriter.setEmail(communityPostReport.getCommunityPost().getWriter().getEmail());
        reportedWriter.setPhoneNumber(communityPostReport.getCommunityPost().getWriter().getPhoneNumber());
        reportedWriter.setProfilePhoto(communityPostReport.getCommunityPost().getWriter().getProfilePhoto());

        reportCommunityPost.setWriter(reportedWriter);
        dto.setReportCommunityPost(reportCommunityPost);

        return dto;
    }
}
