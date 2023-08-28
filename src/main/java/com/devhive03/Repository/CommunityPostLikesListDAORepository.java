package com.devhive03.Repository;

import com.devhive03.Model.DAO.CommentsLikesList;
import com.devhive03.Model.DAO.CommunityPostLikesList;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityPostLikesListDAORepository extends JpaRepository<CommunityPostLikesList, Long> {
    void deleteByUser_IdAndLikedCommunityPost_CommunityPostID(Long userID, Long postID);
    Optional<CommunityPostLikesList> findByLikedCommunityPostAndUser(CommunityPosts communityPost, User user);
    Long countByLikedCommunityPost(CommunityPosts likedCommunityPost);
}
