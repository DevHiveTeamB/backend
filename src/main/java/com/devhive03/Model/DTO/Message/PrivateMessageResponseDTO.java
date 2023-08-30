package com.devhive03.Model.DTO.Message;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PrivateMessageResponseDTO {
    private Opponent opponent;
    private post post;
    private List<PrivateMessageDTO> privateMessageDTOS;

    @Data
    public static class post{
        private Long postid;
        private String postname;
        private Integer price;
        private String postUsername;
    }
    @Data
    public static class Opponent {
        private Long id;
        private String username;
        private String profilePhoto;
        private Long Rating;
        private String introduction;
        private Timestamp JoinDate;
    }
}
