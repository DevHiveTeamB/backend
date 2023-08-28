package com.devhive03.Repository;

import com.devhive03.Model.DAO.CommentsReports;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CommentsReportsDAORepository extends JpaRepository<CommentsReports, Long> {
    List<CommentsReports> findAllByUserId(Long userID);
}
