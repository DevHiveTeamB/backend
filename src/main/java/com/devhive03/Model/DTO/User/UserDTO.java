package com.devhive03.Model.DTO.User;

import com.devhive03.Model.DAO.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private Long kakaoId;
    private String email;
    private String phoneNumber;
    private String profilePhoto;
    private String introduction;
    private Long ratingState;
    private Timestamp JoinDate;

    public static UserDTO of(User user) {
        return UserDTO.builder()
            .userId(user.getId())
            .username(user.getUsername())
            .kakaoId(user.getKakaoId())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .profilePhoto(user.getProfilePhoto())
            .introduction(user.getIntroduction())
            .ratingState(user.getRating())
            .JoinDate(user.getJoinDate())
            .build();
    }
}
