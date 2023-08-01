package com.devhive03.Repository;

import com.devhive03.Model.DAO.CommentsLikesList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsLikesListDAORepository extends JpaRepository<CommentsLikesList, Long> {
}
