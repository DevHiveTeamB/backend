package com.devhive03.Repository;

import com.devhive03.Model.DAO.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRoomDAORepository extends JpaRepository<MessageRoom, Long> {

    List<MessageRoom> findAllByBuyerId(Long userId); //buyer 필드가 특정 userId와 일치하는 모든 MessageRoom 엔티티를 리스트로 반환
    List<MessageRoom> findAllByWriterId(Long userId);
}
