package com.devhive03.Service;

import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DTO.Lecture.LectureDTO;
import com.devhive03.Model.DTO.Post.PostDetailDTO;
import com.devhive03.Repository.PostDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostSearchService {
    @Autowired
    private PostDAORepository postDAORepository;

    //게시물명으로 게시물 검색
    public List<PostDetailDTO> getPostsByPostTitle(String postTitle) {
        // Major에 해당하는 게시물 가져오기
        List<Post> posts = postDAORepository.findByPostTitle(postTitle);
        List<PostDetailDTO> postDetails = convertToPostDetailsDTO(posts);

        return postDetails;
    }

    //교수명으로 게시물 검색
    public List<PostDetailDTO> getPostsByProfessorName(String professorName) {
        List<Post> posts = postDAORepository.findByProfessorName(professorName);
        List<PostDetailDTO> postDetails = convertToPostDetailsDTO(posts);

        return postDetails;
    }

    //강의명으로 게시물 검색
    public List<PostDetailDTO> getPostsByLectureName(String lectureName) {
        List<Post> posts = postDAORepository.findByLectureName(lectureName);
        List<PostDetailDTO> postDetails = convertToPostDetailsDTO(posts);

        return postDetails;
    }

    //전공으로 게시물 검색
    public List<PostDetailDTO> getPostsByMajor(String major) {
        List<Post> posts = postDAORepository.findByMajor(major);
        List<PostDetailDTO> postDetails = convertToPostDetailsDTO(posts);

        return postDetails;
    }

    private List<PostDetailDTO> convertToPostDetailsDTO(List<Post> posts) {
        List<PostDetailDTO> postDetails = new ArrayList<>();
        for (Post post : posts) {
            LectureDTO lectureDTO = LectureDTO.builder()
                    .lectureId(post.getLecture().getLectureID())
                    .lectureName(post.getLecture().getLectureName())
                    .professorName(post.getLecture().getProfessorName())
                    .major(post.getLecture().getMajor())
                    .build();

            PostDetailDTO detail = PostDetailDTO.builder()
                    .postID(post.getPostId())
                    .postTitle(post.getPostTitle())
                    .postDate(post.getPostDate())
                    .price(post.getPrice())
                    .hits(post.getHits())
                    .lecture(lectureDTO) // 강의 정보가 DTO에 직접 포함되므로 직접 할당합니다. 필요한 경우 Lecture를 별도의 DTO로 변환할 수 있습니다.
                    .build();
            postDetails.add(detail);
        }
        return postDetails;
    }

}
