package com.devhive03.Service;

import com.devhive03.Model.DAO.PostReports;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DTO.Report.PostReportRequestDTO;
import com.devhive03.Model.DTO.Report.PostReportResponseDTO;
import com.devhive03.Repository.PostReportsDAORepository;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Repository.PostDAORepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostReportService {
    @Autowired
    private PostReportsDAORepository postReportsDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private PostDAORepository postDAORepository;

    @Transactional
    public List<PostReportResponseDTO> getPostReportByuserId(Long userID) {
        List<PostReports> postReports = postReportsDAORepository.findAllByUserId(userID);

        return postReports.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public String createPostReport(PostReportRequestDTO postReportRequestDTO) {
        User user = userDAORepository.findById(postReportRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postDAORepository.findById(postReportRequestDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        PostReports postReports = new PostReports();
        postReports.setUser(user);
        postReports.setPost(post);
        postReports.setReportContent(postReportRequestDTO.getReportContent());

        postReportsDAORepository.save(postReports);

        return "{\"message\":\"Report created successfully\"}";
    }

    @Transactional
    public String deletePostReport(Long reportID) {
        postReportsDAORepository.deleteById(reportID);
        return "{\"message\":\"Report deleted successfully\"}";
    }

    private PostReportResponseDTO toDTO(PostReports postReport) {
        PostReportResponseDTO dto = new PostReportResponseDTO();

        dto.setReportID(postReport.getReportID());
        dto.setReportContent(postReport.getReportContent());
        dto.setReportData(postReport.getReportDate());

        PostReportResponseDTO.ReportPost reportPost = new PostReportResponseDTO.ReportPost();
        reportPost.setPostID(postReport.getPost().getPostId());
        reportPost.setPostTitle(postReport.getPost().getPostTitle());

        PostReportResponseDTO.ReportPost.ReportedWriter reportedWriter = new PostReportResponseDTO.ReportPost.ReportedWriter();
        reportedWriter.setWriterID(postReport.getPost().getWriter().getId());
        reportedWriter.setWriterName(postReport.getPost().getWriter().getUsername());
        reportedWriter.setEmail(postReport.getPost().getWriter().getEmail());
        reportedWriter.setPhoneNumber(postReport.getPost().getWriter().getPhoneNumber());
        reportedWriter.setProfilePhoto(postReport.getPost().getWriter().getProfilePhoto());

        reportPost.setWriter(reportedWriter);
        dto.setReportPost(reportPost);

        return dto;
    }

}
