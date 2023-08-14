package com.devhive03.Controller.api;


import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Service.MessageRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MessageRoomController {

    private final MessageRoomService messageRoomService;

    @Autowired
    public MessageRoomController(MessageRoomService messageRoomService) {
        this.messageRoomService = messageRoomService;
    }

    @PostMapping("/messagerooms/post")
    public ResponseEntity<MessageRoom> createPost(@RequestBody MessageRoom messageRoom) {
        MessageRoom savedMessageRoom = messageRoomService.createMessageRoom(messageRoom);
        return ResponseEntity.ok(savedMessageRoom);
    }

    @DeleteMapping("/messagerooms/delete/{RoomID}")
    public ResponseEntity<?> deleteMessageRoom(@PathVariable Long RoomID) {
        messageRoomService.deleteMessageRoom(RoomID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/messagerooms/user/get/{userId}")
    public ResponseEntity<List<MessageRoom>> getMessageRoomByUserId(@PathVariable Long userId) {
        List<MessageRoom> messageRooms1 = messageRoomService.getMessageRoomsByBuyerId(userId);
        List<MessageRoom> messageRooms2 = messageRoomService.getMessageRoomsByWriterId(userId);
        messageRooms1.addAll(messageRooms2); //리스트 합쳐서 반환

        return ResponseEntity.ok(messageRooms1);

    }
}
