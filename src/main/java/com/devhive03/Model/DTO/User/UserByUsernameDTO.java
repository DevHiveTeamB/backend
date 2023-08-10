package com.devhive03.Model.DTO.User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class UserByUsernameDTO {
    private Long id;
    private String username;
    private String loginId;
    private String email;
    private String phoneNumber;
    private String profilePhoto;
    private String introduction;
    private Byte membership;
    private Byte certification;
    private Byte ratingState;
}
