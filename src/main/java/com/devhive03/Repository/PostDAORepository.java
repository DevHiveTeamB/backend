package com.devhive03.Repository;

import com.devhive03.Model.DAO.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDAORepository extends JpaRepository<Post, Long> {
}
