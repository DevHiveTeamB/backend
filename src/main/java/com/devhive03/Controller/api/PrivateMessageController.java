package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Service.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrivateMessageController {
    private final PrivateMessageService privateMessageService;

    @Autowired
    public PrivateMessageController(PrivateMessageService privateMessageService) {
        this.privateMessageService = privateMessageService;
    }

    @PutMapping("/private-messages/{messageId}/update-content")
    public ResponseEntity<MessageRoom> updatePrivateMessageContent(
            @PathVariable Long messageId,
            @RequestBody String newContent
    ) {
        MessageRoom updatedMessage = privateMessageService.updatePrivateMessageContent(messageId, newContent);

        if (updatedMessage != null) {
            return ResponseEntity.ok(updatedMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
