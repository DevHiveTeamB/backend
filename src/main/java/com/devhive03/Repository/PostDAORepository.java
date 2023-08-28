package com.devhive03.Repository;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostDAORepository extends JpaRepository<Post, Long> {



    @Query("select p from Post p left join fetch p.lecture left join fetch p.postPictures left join fetch p.writer where p.postId = :postId")
    Optional<Post> findPostId(@Param("postId") Long postId);

    @Query("select p from Post p left join fetch p.postPictures left join fetch p.lecture left join fetch p.writer where p.writer.id = :userId order by p.postDate desc")
    List<Post> findAllByWriterId(@Param("userId") Long userId);

    @Query("select p from Post p left join fetch p.postPictures left join fetch p.lecture left join fetch p.writer where p.writer.id = :userId and p.isSale = false order by p.postDate desc")
    List<Post> findAllByWriterIdAndNotOnSale(@Param("userId") Long userId);

    //title,lectureName, major, professor을 포함한 게시글 검색
    @Query("select p from Post p left join fetch p.postPictures left join fetch p.lecture l where p.postTitle like %:postTitle% or l.lectureName like %:lectureName% or l.major like %:major% or l.professorName like %:professor% order by p.postDate desc")
    List<Post> findAllByPostParamsDTO(@Param("postTitle") String postTitle, @Param("lectureName") String lectureName, @Param("major") String major, @Param("professor") String professor);


    @Query("select p from Post p left join fetch p.lecture l left join fetch p.postPictures left join fetch p.writer where p.postTitle like %:postTitle% order by p.postDate asc")
    List<Post> findByPostTitle(@Param("postTitle") String postTitle);

    @Query("select p from Post p left join fetch p.lecture l left join fetch p.postPictures left join fetch p.writer where l.lectureName like %:lectureName% order by p.postDate desc")
    List<Post> findByLectureName(@Param("lectureName") String lectureName);
    @Query("select p from Post p left join fetch p.lecture l left join fetch p.postPictures left join fetch p.writer where l.professorName like %:professorName% order by p.postDate desc")
    List<Post> findByProfessorName(@Param("professorName") String professorName);
    @Query("select p from Post p left join fetch p.lecture l left join fetch p.postPictures left join fetch p.writer where l.major like %:major% order by p.postDate desc")
    List<Post> findByMajor(@Param("major") String major);

  
}
