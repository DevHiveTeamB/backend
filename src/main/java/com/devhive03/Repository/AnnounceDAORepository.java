package com.devhive03.Repository;

import com.devhive03.Model.DAO.Announce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnounceDAORepository extends JpaRepository<Announce, Long> {
}
