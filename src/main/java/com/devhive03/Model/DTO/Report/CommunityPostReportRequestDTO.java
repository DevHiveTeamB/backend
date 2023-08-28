package com.devhive03.Model.DTO.Report;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommunityPostReportRequestDTO {
    private Long userId;
    private Long communityPostId;
    private String reportContent;
}