package com.devhive03.Repository;

import com.devhive03.Model.DAO.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateMessageDAORepository extends JpaRepository<PrivateMessage, Long> {
}
