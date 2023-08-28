package com.devhive03.Model.DTO.Message;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
@Getter @Setter
public class PrivateMessageDTO {

    private Long messageID;
    private Long messageWriterId; // User의 ID만 저장
    private String state;
    private String privateMessageContent;
    //private Timestamp privateMessageContentDate; // 주석 처리한 부분은 필요에 따라 사용

}
