package com.devhive03.Service;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Model.DAO.User;
import com.devhive03.Repository.MessageRoomDAORepository;
import com.devhive03.Repository.PrivateMessageDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Timestamp;
@Service
public class PrivateMessageService {
    private final PrivateMessageDAORepository privateMessageRepository;

    @Autowired
    public PrivateMessageService(PrivateMessageDAORepository privateMessageRepository) {
        this.privateMessageRepository = privateMessageRepository;
    }

    public PrivateMessage sendPrivateMessage(MessageRoom messageRoom, User messageWriter, String content) {
        // 개인 쪽지 객체 생성 및 필드 설정
        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setMessageRoom(messageRoom);
        privateMessage.setMessageWriter(messageWriter);
        privateMessage.setPrivateMessageContent(content);
        //privateMessage.setPrivateMessageContentDate(new Timestamp(System.currentTimeMillis()));

        // 개인 쪽지 저장
        return privateMessageRepository.save(privateMessage);
    }


}
