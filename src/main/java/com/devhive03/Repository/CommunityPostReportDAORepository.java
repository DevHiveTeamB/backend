package com.devhive03.Repository;

import com.devhive03.Model.DAO.CommunityPostReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityPostReportDAORepository extends JpaRepository<CommunityPostReport, Long> {
    List<CommunityPostReport> findAllByUserId(Long userId);
}
