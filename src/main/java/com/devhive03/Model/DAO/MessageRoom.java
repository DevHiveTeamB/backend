package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messagerooms")
public class MessageRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageroom_id", nullable = false)
    private Long roomID;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "last_message_id")
    private String lastMessageID;

    @Column(name = "last_message_date")
    private Timestamp lastMessageDate;

    @Column(name = "state")
    private Integer state;

    @Column(name = "confirmation_status")
    private Integer confirmationStatus;

    //쪽지방 신고 연관관계
    @OneToOne(mappedBy = "reportedMessageRooms")
    private MessageRoomsReport messageRoomsReport;

    //개인쪽지 연관관계
    @OneToMany(mappedBy = "messageRooms")
    private List<PrivateMessage> privateMessages = new ArrayList<>();

    // Getters and Setters
}