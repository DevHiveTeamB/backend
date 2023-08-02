package com.devhive03.Repository;

import com.devhive03.Model.DAO.CommunityPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostsDAORepository extends JpaRepository<CommunityPosts, Long> {
}
