package com.devhive03.Repository;

import com.devhive03.Model.DAO.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRoomDAORepository extends JpaRepository<MessageRoom, Long> {
    List<MessageRoom> findAllByBuyerIdOrPostWriterId(Long userId, Long userId1);
}
