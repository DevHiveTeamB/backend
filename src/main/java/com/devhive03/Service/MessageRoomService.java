package com.devhive03.Service;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Model.DAO.User;
import com.devhive03.Repository.MessageRoomDAORepository;
import com.devhive03.Repository.PrivateMessageDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MessageRoomService {
    private final MessageRoomDAORepository messageRoomRepository;
    private final PrivateMessageDAORepository privateMessageRepository;

    public MessageRoomService(MessageRoomDAORepository messageRoomRepository, PrivateMessageDAORepository privateMessageRepository) {
        this.messageRoomRepository = messageRoomRepository;
        this.privateMessageRepository = privateMessageRepository;
    }

    public List<MessageRoom> getMessageRoomsByUserId(Long userId) {
        // 유저 아이디로 쪽지방을 가져옵니다.
        List<MessageRoom> messageRooms = messageRoomRepository.findAllByBuyerIdOrPostWriterId(userId, userId);

        // 각 쪽지방의 마지막 메시지를 가져오고, 이를 각 쪽지방에 추가합니다.
        for (MessageRoom room : messageRooms) {
            PrivateMessage lastMessage = getLastMessageInRoom(room.getId());
            room.setLastMessage(lastMessage);
            room.setLastMessageDate(lastMessage);
        }

        return messageRooms;
    }

    private PrivateMessage getLastMessageInRoom(Long roomId) {
        // 특정 쪽지방에 속한 모든 메시지를 시간순으로 정렬하고 가장 최근 메시지를 선택합니다.
        return privateMessageRepository.findTop1ByMessageRoomsIdOrderByPrivateMessageContentDateDesc(roomId);
    }

    public List<PrivateMessage> getMessagesByMessageRoomId(Long messageRoomId) {
        // MessageRoomDAORepository에서 해당 메시지 룸을 조회합니다.
        Optional<MessageRoom> messageRoomOptional = messageRoomRepository.findById(messageRoomId);

        if (messageRoomOptional.isPresent()) {
            MessageRoom messageRoom = messageRoomOptional.get();
            return messageRoom.getPrivateMessages();
        } else {
            // 메시지 룸이 존재하지 않을 경우 빈 리스트 반환 또는 예외 처리
            return Collections.emptyList(); // 빈 리스트 반환
        }
    }

    public MessageRoom createMessageRoom(Post post, User buyer) {
        MessageRoom messageRoom = new MessageRoom();
        messageRoom.setBuyer(buyer);
        messageRoom.setPost(post);

        // 나머지 필드 설정
        messageRoomRepository.save(messageRoom);

        return messageRoom;
    }
}