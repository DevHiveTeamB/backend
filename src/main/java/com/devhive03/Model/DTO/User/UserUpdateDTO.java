package com.devhive03.Model.DTO.User;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDTO {
    @NotNull(message = "아이디를 입력해주세요.")
    private Long id;
    @NotNull(message = "이름를 입력해주세요.")
    private String username;
    private String phoneNumber;
    private String profilePhoto;
    private String introduction;
    private Byte membership;
    private Byte certification;
    private Byte ratingState;

    public static UserUpdateDTO of(UserUpdateDTO userUpdateDTO) {
        return UserUpdateDTO.builder()
            .id(userUpdateDTO.getId())
            .username(userUpdateDTO.getUsername())
            .phoneNumber(userUpdateDTO.getPhoneNumber())
            .profilePhoto(userUpdateDTO.getProfilePhoto())
            .introduction(userUpdateDTO.getIntroduction())
            .membership(userUpdateDTO.getMembership())
            .certification(userUpdateDTO.getCertification())
            .ratingState(userUpdateDTO.getRatingState())
            .build();
    }
}
