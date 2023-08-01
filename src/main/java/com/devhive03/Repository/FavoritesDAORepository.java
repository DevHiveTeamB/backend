package com.devhive03.Repository;

import com.devhive03.Model.DAO.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesDAORepository extends JpaRepository<Favorites, Long> {
}
