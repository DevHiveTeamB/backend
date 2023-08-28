package com.devhive03.Repository;

import com.devhive03.Model.DAO.Favorites;
import com.devhive03.Model.DAO.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesDAORepository extends JpaRepository<Favorites, Long> {
    void deleteByUser_IdAndPost_PostId(Long userID, Long postID);
    List<Favorites> findAllByUser_Id(Long userID);
}
