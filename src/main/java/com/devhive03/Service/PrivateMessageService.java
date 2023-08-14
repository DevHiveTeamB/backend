package com.devhive03.Service;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Repository.PrivateMessageDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrivateMessageService {
    private final PrivateMessageDAORepository privateMessageRepository;

    @Autowired
    public PrivateMessageService(PrivateMessageDAORepository privateMessageRepository) {
        this.privateMessageRepository = privateMessageRepository;
    }

    @Transactional
    public MessageRoom updatePrivateMessageContent(Long messageId, String newContent) {
        PrivateMessage privateMessage = privateMessageRepository.findById(messageId).orElse(null);

        if (privateMessage != null) {
            privateMessage.setPrivateMessageContent(newContent);

            // 해당 PrivateMessage가 속한 MessageRoom을 가져옴
            MessageRoom messageRoom = privateMessage.getMessageRooms();
            // PrivateMessage를 저장하고 MessageRoom을 반환
            privateMessageRepository.save(privateMessage);
            return messageRoom;
        }

        return null;
    }
}
