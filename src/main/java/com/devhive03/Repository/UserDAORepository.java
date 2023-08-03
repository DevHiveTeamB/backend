package com.devhive03.Repository;

import com.devhive03.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAORepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
