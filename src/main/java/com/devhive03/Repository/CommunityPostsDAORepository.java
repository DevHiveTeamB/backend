package com.devhive03.Repository;

import com.devhive03.Model.DAO.CommunityPosts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityPostsDAORepository extends JpaRepository<CommunityPosts, Long> {
    List<CommunityPosts> findAllByWriterIdOrderByCommunityPostDateDesc(Long writerId);
    List<CommunityPosts> findAllByOrderByCommunityPostDateDesc();
}
