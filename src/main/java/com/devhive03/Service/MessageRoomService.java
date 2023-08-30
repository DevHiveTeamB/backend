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
import java.util.Iterator;
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

        // 리스트를 안전하게 순회하기 위한 Iterator 객체 생성
        Iterator<MessageRoom> iterator = messageRooms.iterator();

        while (iterator.hasNext()) {
            MessageRoom room = iterator.next();
            Optional<PrivateMessage> lastMessage = getLastMessageInRoom(room.getId());
            if(lastMessage.isEmpty()) {
                // 조건에 맞는 객체를 리스트에서 제거
                iterator.remove();
            }
            else {
                room.setLastMessage(lastMessage.get());
                room.setLastMessageDate(lastMessage.get().getPrivateMessageContentDate());
            }
        }

        return messageRooms;
    }


    public Optional<PrivateMessage> getLastMessageInRoom(Long roomId) {
        // roomId에 해당하는 쪽지방에서 가장 최근의 메시지를 찾는 쿼리를 작성
        Optional<PrivateMessage> lastMessage = privateMessageRepository.findFirstByMessageRoomsRoomIDOrderByPrivateMessageContentDateDesc(roomId);
        return lastMessage;
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

    public MessageRoom findMessageRoom(Post post, User buyer) {
        // 해당 게시물과 구매자에 대한 메시지 룸을 찾습니다.
        return messageRoomRepository.findByPostAndBuyer(post, buyer);
    }

    public MessageRoom createMessageRoom(Post post, User buyer) {
        MessageRoom messageRoom = new MessageRoom();
        messageRoom.setBuyer(buyer);
        messageRoom.setPost(post);

        messageRoomRepository.save(messageRoom);
        return messageRoom;
    }
}