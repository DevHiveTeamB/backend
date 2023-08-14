package com.devhive03.Model.DTO.User;

import com.devhive03.Model.DAO.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private Long kakaoId;
    private String email;
    private String phoneNumber;
    private String profilePhoto;
    private String introduction;
    private Long membership;
    private Long certification;
    private Long ratingState;

    public static UserDTO of(User user) {
        return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .kakaoId(user.getKakaoId())
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
