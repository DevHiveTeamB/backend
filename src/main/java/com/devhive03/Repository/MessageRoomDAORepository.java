package com.devhive03.Repository;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRoomDAORepository extends JpaRepository<MessageRoom, Long> {
    List<MessageRoom> findAllByBuyerIdOrPost_WriterIdOrderByLastMessageDateDesc(Long userId, Long userId1);

    MessageRoom findByPostAndBuyer(Post post, User buyer);
}
