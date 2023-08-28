package com.devhive03.Model.DTO.Report;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommunityPostReportResponseDTO {
    private Long reportID;
    private ReportCommunityPost reportCommunityPost;
    private String reportContent;
    private Timestamp reportData;

    @Data
    public static class ReportCommunityPost{
        private Long postID;
        private String postTitle;
        private CommunityReportedWriter writer;

        @Data
        public static class CommunityReportedWriter{
            private Long WriterID;
            private String WriterName;
            private String email;
            private String phoneNumber;
            private String profilePhoto;
        }
    }
}
