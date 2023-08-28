package com.devhive03.Model.DTO.Report;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostReportRequestDTO {
    private Long userId;
    private Long postId;
    private String reportContent;
}
