package com.devhive03.Repository;

import com.devhive03.Model.DAO.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrivateMessageDAORepository extends JpaRepository<PrivateMessage, Long> {
    // 특정 쪽지방에 속한 메시지를 시간순으로 정렬하고 가장 최근 메시지를 선택합니다.
    Optional<PrivateMessage> findFirstByMessageRoomsRoomIDOrderByPrivateMessageContentDateDesc(Long roomId);
    List<PrivateMessage> findAllByMessageRoomsRoomIDOrderByPrivateMessageContentDateDesc(Long messagerommId);
}
