package com.devhive03.Repository;

import com.devhive03.Model.DAO.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsDAORepository extends JpaRepository<Comments, Long> {
}
