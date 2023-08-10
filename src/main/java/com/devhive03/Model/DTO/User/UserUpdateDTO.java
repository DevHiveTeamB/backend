package com.devhive03.Model.DTO.User;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDTO {
    @NotNull(message = "아이디를 입력해주세요.")
    private Long id;
    private String username;
    private String phoneNumber;
    private String profilePhoto;
    private String introduction;
    private String membership;
    private String certification;
    private String ratingState;
}
