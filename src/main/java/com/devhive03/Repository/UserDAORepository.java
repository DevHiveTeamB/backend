package com.devhive03.Repository;

import com.devhive03.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAORepository extends JpaRepository<User, Long> {
}
