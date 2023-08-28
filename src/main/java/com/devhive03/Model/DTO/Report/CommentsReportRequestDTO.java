package com.devhive03.Model.DTO.Report;

import lombok.Data;

@Data
public class CommentsReportRequestDTO {
    private Long userId;
    private Long commentId;
    private String reportContent;
}
