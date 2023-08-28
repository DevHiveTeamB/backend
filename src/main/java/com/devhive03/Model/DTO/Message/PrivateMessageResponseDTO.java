package com.devhive03.Model.DTO.Message;

import lombok.Data;

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
    }
}
