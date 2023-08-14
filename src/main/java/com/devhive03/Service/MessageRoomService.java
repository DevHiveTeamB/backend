package com.devhive03.Service;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Repository.MessageRoomDAORepository;
import com.devhive03.Repository.PostDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRoomService {
    private final MessageRoomDAORepository messageRoomRepository;

    @Autowired
    public MessageRoomService(MessageRoomDAORepository messageRoomRepository) {
        this.messageRoomRepository = messageRoomRepository;
    }

    public MessageRoom createMessageRoom(MessageRoom messageRoom) { //메시지룸 생성
        return messageRoomRepository.save(messageRoom);
    }
    public List<MessageRoom> getMessageRoomsByBuyerId(Long userId) {
        return messageRoomRepository.findAllByBuyerId(userId); //getMessageRoomsByBuyerId 메서드를 호출하여 특정 userId에 해당하는 모든 메시지 룸을 가져옴
    }
    public List<MessageRoom> getMessageRoomsByWriterId(Long userId) {
        return messageRoomRepository.findAllByWriterId(userId);
    }
    public void deleteMessageRoom(Long roomID){
        messageRoomRepository.deleteById(roomID);
    }
}
