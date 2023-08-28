package com.devhive03.Repository;

import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DAO.CommentsLikesList;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsLikesListDAORepository extends JpaRepository<CommentsLikesList, Long> {
    void deleteByUser_IdAndComments_CommentsID(Long userID, Long commentsID);
    Optional<CommentsLikesList> findByCommentsAndUser(Comments comments, User user);
    Long countByComments(Comments comments);

}
