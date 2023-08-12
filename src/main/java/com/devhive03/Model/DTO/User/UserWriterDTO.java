package com.devhive03.Model.DTO.User;

import com.devhive03.Model.DAO.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserWriterDTO {
    private Long id;
    private String username;

    public static UserWriterDTO of(User user) {
        return UserWriterDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .build();
    }
}
