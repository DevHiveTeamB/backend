package com.devhive03.Model.DTO.User;

import com.devhive03.Model.DAO.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
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

    public static UserDTO of(User user) {
        return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .loginId(user.getLoginId())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .profilePhoto(user.getProfilePhoto())
            .introduction(user.getIntroduction())
            .membership(user.getMembership())
            .certification(user.getCertification())
            .ratingState(user.getRatingState())
            .build();
    }
}
