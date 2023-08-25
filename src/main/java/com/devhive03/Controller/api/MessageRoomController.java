package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.Message.MessageRoomDTO;
import com.devhive03.Model.DTO.Message.PrivateMessageDTO;
import com.devhive03.Model.DTO.Message.UserMessageRoomResponse;
import com.devhive03.Repository.PostDAORepository;
import com.devhive03.Service.MessageRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devhive03.Repository.UserDAORepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "메시지룸", description = "메시지룸 API")
@RestController
@RequestMapping("/message-rooms")
public class MessageRoomController {

    private final MessageRoomService messageRoomService;
    private final UserDAORepository userDAORepository;
    private final PostDAORepository postDAORepository;

    @Autowired
    public MessageRoomController(MessageRoomService messageRoomService, UserDAORepository userDAORepository, PostDAORepository postDAORepository) {
        this.messageRoomService = messageRoomService;
        this.userDAORepository = userDAORepository;
        this.postDAORepository = postDAORepository;
    }

    @Operation(summary = "유저 메시지룸 조회", description = "user에 속하는 메시지룸들 정보 조회")
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

    @Operation(summary = "메시지룸 조회", description = "메시지룸에 해당하는 모든 메시지 조회")
    @GetMapping("/messages/{messageRoomId}")
    public ResponseEntity<List<PrivateMessageDTO>> getMessagesByMessageRoomId(@PathVariable Long messageRoomId) {
        List<PrivateMessage> messages = messageRoomService.getMessagesByMessageRoomId(messageRoomId);
        List<PrivateMessageDTO> privateMessageDTOS = new ArrayList<>();

        for (PrivateMessage privateMessage : messages) {
            PrivateMessageDTO privateMessageDTO = new PrivateMessageDTO();

            privateMessageDTO.setMessageID(privateMessage.getMessageID());
            privateMessageDTO.setMessageWriterId(privateMessage.getMessageWriter().getId()); // User의 ID만 설정
            privateMessageDTO.setPrivateMessageContent(privateMessage.getPrivateMessageContent()); // 수정된 부분

            privateMessageDTOS.add(privateMessageDTO);
        }

        return ResponseEntity.ok(privateMessageDTOS);
    }

    @Operation(summary = "메시지룸 생성", description = "메시지룸 생성")
    @PostMapping("/messagerooms/post") //프론트에서는 확인만하면되니까 굳이 DTO로 할 필요 X
    public ResponseEntity<Long> createMessageRoom(
            @RequestParam Long postId,
            @RequestParam Long buyerId
    ) {
        // buyerId와 writerId를 사용하여 User 엔티티를 가져옵니다.
        Post post = postDAORepository.findById(postId).orElse(null);
        User buyer = userDAORepository.findById(buyerId).orElse(null);


        if (post.getWriter().getId() != null && buyer != null) {
            MessageRoom messageRoom = messageRoomService.createMessageRoom(post, buyer);
            if (messageRoom != null) {
                Long roomID = messageRoom.getRoomID();
                return ResponseEntity.ok(roomID);
            } else {
                return ResponseEntity.badRequest().body(null); // 실패 시 적절한 응답 반환
            }
        }
        return null;
    }
}