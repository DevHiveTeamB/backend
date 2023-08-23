package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Service.MessageRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message-rooms")
public class MessageRoomController {

    private final MessageRoomService messageRoomService;

    @Autowired
    public MessageRoomController(MessageRoomService messageRoomService) {
        this.messageRoomService = messageRoomService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MessageRoom>> getMessageRoomsByUserId(@PathVariable Long userId) {
        List<MessageRoom> messageRooms = messageRoomService.getMessageRoomsByUserId(userId);
        return ResponseEntity.ok(messageRooms);
    }

    @GetMapping("/{messageRoomId}/messages")
    public ResponseEntity<List<PrivateMessage>> getMessagesByMessageRoomId(@PathVariable Long messageRoomId) {
        List<PrivateMessage> messages = messageRoomService.getMessagesByMessageRoomId(messageRoomId);
        return ResponseEntity.ok(messages);
    }
}