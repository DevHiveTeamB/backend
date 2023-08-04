package com.devhive03.Service;

import com.devhive03.Model.DAO.Post;
import com.devhive03.Repository.PostDAORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostDAORepository postDAORepository;

    // Create
    public Post createPost(Post post) {
        return postDAORepository.save(post);
    }

    // Read Single
    public Optional<Post> getPost(Long id) {
        return postDAORepository.findById(id);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postDAORepository.findAllByWriterId(userId);
    }

    // Read All
    public List<Post> getAllPosts() {
        return postDAORepository.findAll();
    }

    // Update
    public Post updatePost(Long postId, Post postDetails) {
        return postDAORepository.findById(postId)
                .map(post -> {
                    post.setPostTitle(postDetails.getPostTitle());
                    post.setPostContent(postDetails.getPostContent());
                    // Add the rest of the post fields here
                    return postDAORepository.save(post);
                })
                .orElseThrow(() -> new RuntimeException("Post with id " + postId + " not found"));
    }

    // Delete
    public void deletePost(Long id) {
        postDAORepository.deleteById(id);
    }


    public List<Post> getPostsByTitle(String postTitle) {
        return postDAORepository.findByPostTitle(postTitle);
    }

    public List<Post> getPostsByLectureName(String lectureName) {
        return postDAORepository.findByLecture_LectureName(lectureName);
    }

    public List<Post> getPostsByProfessorName(String professorName) {
        return postDAORepository.findByLecture_ProfessorName(professorName);
    }

    public List<Post> getPostsByMajor(String major) {
        return postDAORepository.findByLecture_Major(major);
    }
}
