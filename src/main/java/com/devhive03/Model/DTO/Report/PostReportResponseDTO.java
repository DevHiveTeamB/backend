package com.devhive03.Model.DTO.Report;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostReportResponseDTO {
    private Long reportID;
    private ReportPost reportPost;
    private String reportContent;
    private Timestamp reportData;

    @Data
    public static class ReportPost{
        private Long postID;
        private String postTitle;
        private ReportedWriter writer;

        @Data
        public static class ReportedWriter{
            private Long WriterID;
            private String WriterName;
            private String email;
            private String phoneNumber;
            private String profilePhoto;
        }
    }
}
