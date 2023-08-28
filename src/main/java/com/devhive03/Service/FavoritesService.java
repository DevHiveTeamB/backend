package com.devhive03.Service;

import com.devhive03.Model.DAO.Favorites;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.Lecture.LectureDTO;
import com.devhive03.Model.DTO.Post.PostDetailDTO;
import com.devhive03.Repository.FavoritesDAORepository;
import com.devhive03.Repository.PostDAORepository;
import com.devhive03.Repository.UserDAORepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritesService {
    @Autowired
    private FavoritesDAORepository favoritesDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private PostDAORepository postDAORepository;

    @Transactional
    public List<PostDetailDTO> getPostFavoritesByuserId(Long userID) {
        List<Favorites> favorites = favoritesDAORepository.findAllByUser_Id(userID);
        return convertFavoritesToPostDetailDTO(favorites);
    }

    @Transactional
    public String createFavorites(Long userID, Long postID){
        Optional<User> user = userDAORepository.findById(userID);
        Optional<Post> post = postDAORepository.findPostId(postID);

        Favorites favorites = new Favorites();
        favorites.setUser(user.get());
        favorites.setPost(post.get());

        favoritesDAORepository.save(favorites);
        return "{\"message\":\"Favorites created successfully\"}";
    }

    @Transactional
    public String deleteFavorites(Long userID, Long postID){
        favoritesDAORepository.deleteByUser_IdAndPost_PostId(userID, postID);
        return "{\"message\":\"Favorites deleted successfully\"}";
    }

    private List<PostDetailDTO> convertFavoritesToPostDetailDTO(List<Favorites> favoritesList) {
        List<PostDetailDTO> postDetailDTOList = new ArrayList<>();

        for (Favorites favorite : favoritesList) {
            Post post = favorite.getPost();
            LectureDTO lectureDTO = LectureDTO.of(post.getLecture());  // 이 부분은 LectureDTO의 of 메서드가 어떻게 구현되었는지 모르므로 예상 코드입니다.

            PostDetailDTO postDetailDTO = PostDetailDTO.builder()
                    .postID(post.getPostId())
                    .postTitle(post.getPostTitle())
                    .postDate(post.getPostDate())
                    .price(post.getPrice())
                    .hits(post.getHits())
                    .lecture(lectureDTO)
                    .build();

            postDetailDTOList.add(postDetailDTO);
        }

        return postDetailDTOList;
    }
}
