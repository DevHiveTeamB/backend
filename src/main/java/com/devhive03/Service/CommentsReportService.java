package com.devhive03.Service;

import com.devhive03.Model.DAO.CommentsReports;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DTO.Report.CommentsReportRequestDTO;
import com.devhive03.Model.DTO.Report.CommentsReportResponseDTO;
import com.devhive03.Model.DTO.Report.CommunityPostReportResponseDTO;
import com.devhive03.Repository.CommentsReportsDAORepository;
import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Repository.CommentsDAORepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsReportService {
    @Autowired
    private CommentsReportsDAORepository commentsReportsDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private CommentsDAORepository commentsDAORepository;

    @Transactional
    public List<CommentsReportResponseDTO> getReportByUserId(Long userID) {
        List<CommentsReports> commentsReports = commentsReportsDAORepository.findAllByUserId(userID);

        return commentsReports.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public String createCommentReport(CommentsReportRequestDTO commentsReportRequestDTO) {
        User user = userDAORepository.findById(commentsReportRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comments comments = commentsDAORepository.findById(commentsReportRequestDTO.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        CommentsReports commentsReports = new CommentsReports();
        commentsReports.setUser(user);
        commentsReports.setComments(comments);
        commentsReports.setReportContent(commentsReportRequestDTO.getReportContent());

        commentsReportsDAORepository.save(commentsReports);

        return "{\"message\":\"Report created successfully\"}";
    }

    @Transactional
    public String deleteCommentReport(Long reportID) {
        commentsReportsDAORepository.deleteById(reportID);
        return "{\"message\":\"Report deleted successfully\"}";
    }

    private CommentsReportResponseDTO toDTO(CommentsReports commentsReport) {
        CommentsReportResponseDTO dto = new CommentsReportResponseDTO();
        dto.setReportID(commentsReport.getReportID());

        CommentsReportResponseDTO.ReportComment reportComment = new CommentsReportResponseDTO.ReportComment();
        dto.setReportComment(reportComment);

        CommentsReportResponseDTO.ReportComment.CommentReportedWriter commentReportedWriter = new CommentsReportResponseDTO.ReportComment.CommentReportedWriter();
        dto.getReportComment().setWriter(commentReportedWriter);

        dto.getReportComment().setCommentID(commentsReport.getComments().getCommentsID());

        dto.getReportComment().getWriter().setWriterID(commentsReport.getComments().getUser().getId());
        dto.getReportComment().getWriter().setWriterName(commentsReport.getUser().getUsername());
        dto.getReportComment().getWriter().setEmail(commentsReport.getUser().getEmail());
        dto.getReportComment().getWriter().setPhoneNumber(commentsReport.getUser().getPhoneNumber());
        dto.getReportComment().getWriter().setProfilePhoto(commentsReport.getUser().getProfilePhoto());

        dto.setReportContent(commentsReport.getReportContent());
        dto.setReportData(commentsReport.getReportDate());

        return  dto;
    }
}
