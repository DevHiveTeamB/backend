package com.devhive03.Service;

import com.devhive03.Model.DAO.Comments;
import com.devhive03.Model.DAO.CommunityPosts;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsDetailsResponseDTO;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsRequestDTO;
import com.devhive03.Model.DTO.CommunityPost.CommunityPostsResponseDTO;
import com.devhive03.Repository.CommentsLikesListDAORepository;
import com.devhive03.Repository.CommunityPostLikesListDAORepository;
import com.devhive03.Repository.CommunityPostsDAORepository;
import com.devhive03.Repository.UserDAORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityPostsService {
    @Autowired
    private CommunityPostsDAORepository communityPostsDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private CommunityPostLikesListDAORepository communityPostLikesListDAORepository;
    @Autowired
    private CommentsLikesListDAORepository commentsLikesListDAORepository;

    //자세한 내용 보내기
    public ResponseEntity<CommunityPostsDetailsResponseDTO> getCommunityPost(Long postid, Long userID) {
        Optional<CommunityPosts> communityPostOpt = communityPostsDAORepository.findById(postid);
        CommunityPosts communityPost = communityPostOpt.get();
        User user;
        if(userID!=null) {
            Optional<User> userOpt = userDAORepository.findById(userID);

            if (!communityPostOpt.isPresent() || !userOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            user = userOpt.get();
        }
        else{
            user = null;
        }
        CommunityPostsDetailsResponseDTO responseDTO = convertEntityToDetailsResponseDTO(communityPost, user);

        // Check if the user has liked this post
        Boolean hasUserLikedPost = communityPostLikesListDAORepository
                .findByLikedCommunityPostAndUser(communityPost, user)
                .isPresent();
        responseDTO.setIsCommunityPostLikes(hasUserLikedPost);

        // Get the count of likes for this post
        Long communityPostLikesCount = communityPostLikesListDAORepository
                .countByLikedCommunityPost(communityPost);
        responseDTO.setCommunityPostLikesCount(communityPostLikesCount); // set the count here

        return ResponseEntity.ok(responseDTO);
    }



    // Read by User ID
    public List<CommunityPostsResponseDTO> getCommunityPostsByUser(Long userId) {
        List<CommunityPosts> communityPosts = communityPostsDAORepository.findAllByWriterId(userId);
        return communityPosts.stream()
                .map(this::convertEntityToResponseDTO)
                .collect(Collectors.toList());
    }

    // Read All
    public List<CommunityPostsResponseDTO> getAllCommunityPosts() {
        List<CommunityPosts> communityPosts = communityPostsDAORepository.findAll();
        List<CommunityPostsResponseDTO> collect = communityPosts.stream()
                .map(this::convertEntityToResponseDTO)
                .collect(Collectors.toList());
        return collect;
    }

    public String createCommunityPost(CommunityPostsRequestDTO communityPostRequest) throws Exception {
        CommunityPosts communityPost = new CommunityPosts();

        // Convert DTO to Entity
        Optional<User> writerOpt = userDAORepository.findById(communityPostRequest.getWriterID());

        if (!writerOpt.isPresent()) {
            throw new Exception("User not found with provided writerID");
        }

        communityPost.setWriter(writerOpt.get());
        communityPost.setCommunityPostTitle(communityPostRequest.getCommunityPostTitle());
        communityPost.setCommunityPostContent(communityPostRequest.getCommunityPostContent());
        communityPostsDAORepository.save(communityPost);

        try {
            return String.format("{\"userID\":\"%d\"}", writerOpt.get().getId());
        } catch (Exception e) {
            throw new Exception("Error occurred while saving the CommunityPost", e);
        }
    }

    // Update
    public String updateCommunityPost(Long communityPostID, CommunityPostsRequestDTO communityPostRequest) {
        return communityPostsDAORepository.findById(communityPostID)
                .map(communityPost -> {
                    communityPost.setCommunityPostTitle(communityPostRequest.getCommunityPostTitle());
                    communityPost.setCommunityPostContent(communityPostRequest.getCommunityPostContent());
                    communityPostsDAORepository.save(communityPost);
                    // Add the rest of the post fields here
                    return "{\"message\":\"CommunityPost updated successfully\"}";
                })
                .orElseThrow(() -> new RuntimeException("CommunityPost with id " + communityPostID + " not found"));
    }

    // Delete
    public String deleteCommunityPost(Long id) {
        communityPostsDAORepository.deleteById(id);
        return "{\"message\":\"CommunityPost deleted successfully\"}";
    }


    private CommunityPostsResponseDTO convertEntityToResponseDTO(CommunityPosts communityPost) {
        CommunityPostsResponseDTO dto = new CommunityPostsResponseDTO();

        // Set basic details
        dto.setCommunityPostID(communityPost.getCommunityPostID());
        dto.setCommunityPostTitle(communityPost.getCommunityPostTitle());
        dto.setCommunityPostContent(communityPost.getCommunityPostContent());
        dto.setCommunityPostDate(communityPost.getCommunityPostDate());
        dto.setCommunityPostLikesCount(communityPostLikesListDAORepository.countByLikedCommunityPost(communityPost));

        // Set Writer details
        CommunityPostsResponseDTO.Writer writerDTO = new CommunityPostsResponseDTO.Writer();
        writerDTO.setId(communityPost.getWriter().getId());
        writerDTO.setUsername(communityPost.getWriter().getUsername());
        writerDTO.setLoginId(communityPost.getWriter().getLoginId());
        writerDTO.setProfilePhoto(communityPost.getWriter().getProfilePhoto());
        dto.setWriter(writerDTO);

        // Set Comments IDs
        List<Long> commentIDs = communityPost.getComments().stream()
                .map(Comments::getCommentsID)
                .collect(Collectors.toList());
        dto.setCommentcount(commentIDs.stream().count());

        return dto;
    }


    private CommunityPostsDetailsResponseDTO convertEntityToDetailsResponseDTO(CommunityPosts communityPost, User user) {
        CommunityPostsDetailsResponseDTO dto = new CommunityPostsDetailsResponseDTO();

        dto.setCommunityPostID(communityPost.getCommunityPostID());
        dto.setCommunityPostTitle(communityPost.getCommunityPostTitle());
        dto.setCommunityPostContent(communityPost.getCommunityPostContent());
        dto.setCommunityPostDate(communityPost.getCommunityPostDate());

        // Set Writer details
        CommunityPostsDetailsResponseDTO.Writer writerDTO = new CommunityPostsDetailsResponseDTO.Writer();
        writerDTO.setId(communityPost.getWriter().getId());
        writerDTO.setUsername(communityPost.getWriter().getUsername());
        writerDTO.setLoginId(communityPost.getWriter().getLoginId());
        dto.setWriter(writerDTO);

        // Set Comments
        List<CommunityPostsDetailsResponseDTO.Comment> commentsDTOs = communityPost.getComments().stream()
                .map(comment -> {
                    CommunityPostsDetailsResponseDTO.Comment commentDTO = new CommunityPostsDetailsResponseDTO.Comment();
                    commentDTO.setCommentsID(comment.getCommentsID());

                    CommunityPostsDetailsResponseDTO.Comment.User userDTO = new CommunityPostsDetailsResponseDTO.Comment.User();
                    userDTO.setId(comment.getUser().getId());
                    userDTO.setUsername(comment.getUser().getUsername());
                    userDTO.setLoginId(comment.getUser().getLoginId());

                    Boolean hasUserLikedPost;
                    if(user == null){
                        hasUserLikedPost = false;
                    }
                    else {
                        // Check if the user has liked this post
                        hasUserLikedPost = commentsLikesListDAORepository
                                .findByCommentsAndUser(comment, user)
                                .isPresent();
                    }

                    // Get the count of likes for this post
                    Long communityPostLikesCount = commentsLikesListDAORepository
                            .countByComments(comment);


                    commentDTO.setUser(userDTO);
                    commentDTO.setCommentContent(comment.getCommentContent());
                    commentDTO.setCommentDate(comment.getCommentDate());

                    commentDTO.setIsLikes(hasUserLikedPost);
                    commentDTO.setLikes(communityPostLikesCount);
                    return commentDTO;
                })
                .collect(Collectors.toList());
        dto.setComments(commentsDTOs);

        return dto;
    }
}
