package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.Message.MessageRoomDTO;
import com.devhive03.Model.DTO.Message.PrivateMessageDTO;
import com.devhive03.Model.DTO.Message.PrivateMessageResponseDTO;
import com.devhive03.Repository.MessageRoomDAORepository;
import com.devhive03.Repository.PostDAORepository;
import com.devhive03.Service.MessageRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devhive03.Repository.UserDAORepository;

import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "메시지룸", description = "메시지룸 API")
@RestController
@RequestMapping("/message-rooms")
public class MessageRoomController {

    private final MessageRoomService messageRoomService;
    private final MessageRoomDAORepository messageRoomDAORepository;
    private final UserDAORepository userDAORepository;
    private final PostDAORepository postDAORepository;

    @Autowired
    public MessageRoomController(MessageRoomService messageRoomService, UserDAORepository userDAORepository, PostDAORepository postDAORepository, MessageRoomDAORepository messageRoomDAORepository) {
        this.messageRoomService = messageRoomService;
        this.userDAORepository = userDAORepository;
        this.postDAORepository = postDAORepository;
        this.messageRoomDAORepository = messageRoomDAORepository;
    }

    @Operation(summary = "해당 유저의 메시지룸 리스트 조회", description = "user에 속하는 메시지룸들 정보 조회")
    @GetMapping("messagerooms/user/get/{userId}")
    public ResponseEntity<List<MessageRoomDTO>> getMessageRoomsByUserId(@PathVariable Long userId) {
        List<MessageRoom> messageRooms = messageRoomService.getMessageRoomsByUserId(userId);

        List<MessageRoomDTO> messageRoomDTOS = messageRooms.stream()
                .map(room -> room.toMessageRoomDTO(userId))
                .collect(Collectors.toList());

        return ResponseEntity.ok(messageRoomDTOS);
    }

    @Operation(summary = "해당 메시지룸과 쪽지들 조회", description = "메시지룸에 해당하는 모든 메시지 조회")
    @GetMapping("/messages/{messageRoomId}/{opponentId}")
    public ResponseEntity<PrivateMessageResponseDTO> getMessagesByMessageRoomId(
            @PathVariable Long messageRoomId,
            @PathVariable Long opponentId) {
        PrivateMessageResponseDTO privateMessageResponseDTO = new PrivateMessageResponseDTO();

        PrivateMessageResponseDTO.post postPMDTO = new PrivateMessageResponseDTO.post();
        Post post = messageRoomDAORepository.findById(messageRoomId).get().getPost();
        postPMDTO.setPostid(post.getPostId());
        postPMDTO.setPostname(post.getPostTitle());
        postPMDTO.setPrice(post.getPrice());
        postPMDTO.setPostUsername(post.getWriter().getUsername());

        PrivateMessageResponseDTO.Opponent opponent = new PrivateMessageResponseDTO.Opponent();
        Optional<User> user = userDAORepository.findById(opponentId);
        opponent.setId(opponentId);
        opponent.setUsername(user.get().getUsername());
        opponent.setProfilePhoto(user.get().getProfilePhoto());
        opponent.setRating(user.get().getRating());


        List<PrivateMessage> messages = messageRoomService.getMessagesByMessageRoomId(messageRoomId);
        List<PrivateMessageDTO> privateMessageDTOS = new ArrayList<>();

        for (PrivateMessage privateMessage : messages) {
            PrivateMessageDTO privateMessageDTO = new PrivateMessageDTO();

            privateMessageDTO.setMessageID(privateMessage.getMessageID());
            privateMessageDTO.setMessageWriterId(privateMessage.getMessageWriter().getId()); // User의 ID만 설정
            privateMessageDTO.setPrivateMessageContent(privateMessage.getPrivateMessageContent()); // 수정된 부분

            if(opponentId==privateMessage.getMessageWriter().getId())
                privateMessageDTO.setState("보낸");
            else
                privateMessageDTO.setState("받은");

            privateMessageDTOS.add(privateMessageDTO);
        }


        privateMessageResponseDTO.setPost(postPMDTO);
        privateMessageResponseDTO.setOpponent(opponent);
        privateMessageResponseDTO.setPrivateMessageDTOS(privateMessageDTOS);

        return ResponseEntity.ok(privateMessageResponseDTO);
    }

    @Operation(summary = "메시지룸 생성", description = "메시지룸 생성")
    @PostMapping("/messagerooms/post") //프론트에서는 확인만하면되니까 굳이 DTO로 할 필요 X
    public ResponseEntity<Map<String,Object>> createMessageRoom(
            @RequestParam Long postId,
            @RequestParam Long buyerId
    ) {
        // buyerId와 postId를 사용하여 엔티티들을 가져옵니다.
        Post post = postDAORepository.findById(postId).orElse(null);
        User buyer = userDAORepository.findById(buyerId).orElse(null);

        if (post != null && buyer != null) {
            // 기존 메시지 룸을 찾습니다.
            MessageRoom existingRoom = messageRoomService.findMessageRoom(post, buyer);

            if (existingRoom != null) {
                // 기존 메시지 룸이 있으면 해당 룸을 반환합니다.
                Long roomID = existingRoom.getRoomID();
                Long postID = post.getPostId();
                Long buyerID = buyer.getId();
                String postTitle = post.getPostTitle();
                Integer price = post.getPrice();

                Map<String, Object> response = new HashMap<>();
                response.put("roomID", roomID);
                response.put("postID", postID);
                response.put("buyerID", buyerID);
                response.put("title", postTitle);
                response.put("price", price);

                return ResponseEntity.ok(response);
            } else {
                // 기존 메시지 룸이 없으면 새로운 룸을 생성합니다.
                MessageRoom newRoom = messageRoomService.createMessageRoom(post, buyer);
                if (newRoom != null) {
                    Long roomID = newRoom.getRoomID();
                    Long postID = post.getPostId();
                    Long buyerID = buyer.getId();
                    String postTitle = post.getPostTitle();
                    Integer price = post.getPrice();

                    Map<String, Object> response = new HashMap<>();
                    response.put("roomID", roomID);
                    response.put("postID", postID);
                    response.put("buyerID", buyerID);
                    response.put("title", postTitle);
                    response.put("price", price);

                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}