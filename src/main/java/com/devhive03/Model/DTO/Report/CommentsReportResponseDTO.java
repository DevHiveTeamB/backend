package com.devhive03.Model.DTO.Report;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentsReportResponseDTO {
    private Long reportID;
    private ReportComment reportComment;
    private String reportContent;
    private Timestamp reportData;

    @Data
    public static class ReportComment {
        private Long commentID;
        private CommentReportedWriter writer;

        @Data
        public static class CommentReportedWriter {
            private Long WriterID;
            private String WriterName;
            private String email;
            private String phoneNumber;
            private String profilePhoto;
        }
    }
}
