package com.devhive03.Model.DAO;

import com.devhive03.Model.DTO.Message.MessageRoomDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@Table(name = "messagerooms")
public class MessageRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageroom_id", nullable = false)
    private Long roomID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "last_message")
    private String lastMessageContent = "";

    @Column(name = "last_message_date")
    private Timestamp lastMessageDate = new java.sql.Timestamp(System.currentTimeMillis());

    //쪽지방 신고 연관관계
    @OneToOne(mappedBy = "reportedMessageRooms", fetch = FetchType.LAZY)
    private MessageRoomsReport messageRoomsReport;

    //개인쪽지 연관관계
    @OneToMany(mappedBy = "messageRooms") //메시지룸안에 여러개의 메시지 존재
    private List<PrivateMessage> privateMessages = new ArrayList<>();


    public String getLastMessageContent() {
        if (!privateMessages.isEmpty()) {
            PrivateMessage lastMessage = privateMessages.get(privateMessages.size() - 1);
            return lastMessage.getPrivateMessageContent();
        } else {
            return "No message available";
        }
    }

    public Long getId() {
        return roomID;
    }
    public void setLastMessage(PrivateMessage lastMessage) {
         if (lastMessage != null) {
            this.lastMessageContent = lastMessage.getPrivateMessageContent();
        } else {
            this.lastMessageContent = "";
        }
    }

    public List<PrivateMessage> getPrivateMessages() {
        return privateMessages;
    }

    public void setPrivateMessages(List<PrivateMessage> privateMessages) {
        this.privateMessages = privateMessages;
    }
    // Getters and Setters

    public MessageRoomDTO toMessageRoomDTO(Long userId) {
        MessageRoomDTO dto = new MessageRoomDTO();
        dto.setRoomID(this.roomID); // 이 부분은 정확한 필드가 무엇인지 모르기 때문에 예시로 작성했습니다. 실제 필드에 맞게 수정해야 합니다.

        if(userId!=this.buyer.getId()) {
            MessageRoomDTO.Opponent opponent = new MessageRoomDTO.Opponent();
            opponent.setId(this.buyer.getId());
            opponent.setUsername(this.buyer.getUsername());
            opponent.setProfilePhoto(this.buyer.getProfilePhoto());
            dto.setOpponent(opponent);
        }
        else {
            MessageRoomDTO.Opponent wr = new MessageRoomDTO.Opponent();
            wr.setId(this.post.getWriter().getId());
            wr.setUsername(this.post.getWriter().getUsername());
            wr.setProfilePhoto(this.post.getWriter().getProfilePhoto());
            dto.setOpponent(wr);
        }

        dto.setPostid(this.post.getPostId());

        dto.setLastMessageData(this.lastMessageContent);
        dto.setLastMessageDate(this.lastMessageDate);
        return dto;
    }
}