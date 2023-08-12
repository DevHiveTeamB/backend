package com.devhive03.Repository;

import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DTO.Post.PostDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostDAORepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p left join fetch p.messageRoom left join fetch p.lecture left join fetch p.postPictures left join fetch p.writer where p.postId = :postId")
    Optional<Post> findPostId(@Param("postId") Long postId);

    List<Post> findAllByWriterId(Long userId);

    List<Post> findByPostTitle(String postTitle);
    List<Post> findByLecture_LectureName(String lectureName);
    List<Post> findByLecture_ProfessorName(String professorName);
    List<Post> findByLecture_Major(String major);
}
