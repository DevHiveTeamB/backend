package com.devhive03.Repository;

import com.devhive03.Model.DAO.PostReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReportsDAORepository extends JpaRepository<PostReports, Long> {
    List<PostReports> findAllByUserId(Long userID);
}
