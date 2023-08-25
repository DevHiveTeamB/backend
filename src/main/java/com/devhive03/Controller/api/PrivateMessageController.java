package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Model.DAO.User;
import com.devhive03.Service.PrivateMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devhive03.Repository.MessageRoomDAORepository;
import com.devhive03.Repository.UserDAORepository;

@Tag(name = "메시지", description = "메시지 API")
@RestController
public class PrivateMessageController {
    private final PrivateMessageService privateMessageService;
    private final MessageRoomDAORepository messageRoomDAORepository;
    private final UserDAORepository userDAORepository;

    @Autowired
    public PrivateMessageController(PrivateMessageService privateMessageService, MessageRoomDAORepository messageRoomDAORepository, UserDAORepository userDAORepository) {
        this.privateMessageService = privateMessageService;
        this.messageRoomDAORepository = messageRoomDAORepository;
        this.userDAORepository = userDAORepository;
    }

    @Operation(summary = "메시지 저장", description = "메시지룸에 메시지들 저장")
    @PostMapping("/privatemessages/post")
    public String sendPrivateMessage(
            @RequestParam Long messageRoomId,
            @RequestParam Long messageWriterId,
            @RequestParam String content
    ) {
        // 여기에서 messageRoomId와 messageWriterId를 사용하여 MessageRoom과 User를 가져오는 작업을 수행

        MessageRoom messageRoom = messageRoomDAORepository.findById(messageRoomId).orElse(null);;
        User messageWriter = userDAORepository.findById(messageWriterId).orElse(null);

        if (messageRoom != null && messageWriter != null) {
            PrivateMessage privateMessage = privateMessageService.sendPrivateMessage(messageRoom, messageWriter, content);
            return "{\"message\":\"success\"}";
        } else {
            return "{\"message\":\"false\"}";
        }
    }
}
