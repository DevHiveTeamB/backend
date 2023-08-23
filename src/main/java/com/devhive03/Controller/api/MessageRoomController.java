package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Model.DTO.Message.MessageRoomDTO;
import com.devhive03.Model.DTO.Message.UserMessageRoomResponse;
import com.devhive03.Service.MessageRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message-rooms")
public class MessageRoomController {

    private final MessageRoomService messageRoomService;

    @Autowired
    public MessageRoomController(MessageRoomService messageRoomService) {
        this.messageRoomService = messageRoomService;
    }

    @GetMapping("/user/{userId}")
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

    @GetMapping("/{messageRoomId}/messages")
    public ResponseEntity<List<PrivateMessage>> getMessagesByMessageRoomId(@PathVariable Long messageRoomId) {
        List<PrivateMessage> messages = messageRoomService.getMessagesByMessageRoomId(messageRoomId);
        return ResponseEntity.ok(messages);
    }
}