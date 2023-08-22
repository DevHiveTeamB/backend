package com.devhive03.Repository;

import com.devhive03.Model.DAO.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateMessageDAORepository extends JpaRepository<PrivateMessage, Long> {
    // 특정 쪽지방에 속한 메시지를 시간순으로 정렬하고 가장 최근 메시지를 선택합니다.
    PrivateMessage findTop1ByMessageRoomsIdOrderByPrivateMessageContentDateDesc(Long roomId);
}
