package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.Message.MessageRoomDTO;
import com.devhive03.Model.DTO.Message.UserMessageRoomResponse;
import com.devhive03.Service.MessageRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devhive03.Repository.UserDAORepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message-rooms")
public class MessageRoomController {

    private final MessageRoomService messageRoomService;
    private UserDAORepository userDAORepository;

    @Autowired
    public MessageRoomController(MessageRoomService messageRoomService) {
        this.messageRoomService = messageRoomService;
    }

    //user에 속하는 메시지룸들 정보 조회
    @GetMapping("messagerooms/user/get/{userId}")
    public ResponseEntity<UserMessageRoomResponse> getMessageRoomsByUserId(@PathVariable Long userId) {
        List<MessageRoom> messageRooms = messageRoomService.getMessageRoomsByUserId(userId);
        UserMessageRoomResponse userMessageRoomResponse = new UserMessageRoomResponse();

        List<MessageRoomDTO> messageRoomDTOS = messageRooms.stream()
                .map(MessageRoom::toMessageRoomDTO)
                .collect(Collectors.toList());

        userMessageRoomResponse.setUserID(userId);
        userMessageRoomResponse.setMessageRoom(messageRoomDTOS);

        return ResponseEntity.ok(userMessageRoomResponse);
    }

    //메시지룸 전체 조회
    @GetMapping("/messages/{messageRoomId}")
    public ResponseEntity<List<PrivateMessage>> getMessagesByMessageRoomId(@PathVariable Long messageRoomId) {
        List<PrivateMessage> messages = messageRoomService.getMessagesByMessageRoomId(messageRoomId);
        return ResponseEntity.ok(messages);
    }
    
    //메시지룸 생성
    @PostMapping("/messagerooms/post") //프론트에서는 확인만하면되니까 굳이 DTO로 할 필요 X
    public ResponseEntity<MessageRoom> createMessageRoom(
            @RequestParam Long buyerId,
            @RequestParam Long writerId
    ) {
        // buyerId와 writerId를 사용하여 User 엔티티를 가져옵니다.
        User buyer = userDAORepository.findById(buyerId).orElse(null);
        User writer = userDAORepository.findById(writerId).orElse(null);


        if (buyer != null && writer != null) {
            MessageRoom messageRoom = messageRoomService.createMessageRoom(buyer, writer);
            return ResponseEntity.ok(messageRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}