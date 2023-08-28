package com.devhive03.Repository;

import com.devhive03.Model.DAO.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureDAORepository extends JpaRepository<Lecture, Long> {
    @Query("select l from Lecture l where l.lectureName like %:lectureName%")
    List<Lecture> findByLectureName(@Param("lectureName") String lectureName);

    @Query("select l from Lecture l where l.major like %:major%")
    List<Lecture> findByMajor(@Param("major") String major);

    @Query("select l from Lecture l where l.professorName like %:professor%")
    List<Lecture> findByProfessorName(@Param("professor") String professor);

}
