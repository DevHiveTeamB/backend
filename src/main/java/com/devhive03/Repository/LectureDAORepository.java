package com.devhive03.Repository;

import com.devhive03.Model.DAO.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureDAORepository extends JpaRepository<Lecture, Long> {
}
