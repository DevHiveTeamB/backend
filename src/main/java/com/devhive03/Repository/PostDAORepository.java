package com.devhive03.Repository;

import com.devhive03.Model.DAO.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDAORepository extends JpaRepository<Post, Long> {
    List<Post> findAllByWriterId(Long userId);

    List<Post> findByPostTitle(String postTitle);
    List<Post> findByLecture_LectureName(String lectureName);
    List<Post> findByLecture_ProfessorName(String professorName);
    List<Post> findByLecture_Major(String major);
}
