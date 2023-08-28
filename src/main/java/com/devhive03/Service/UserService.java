package com.devhive03.Service;

import com.devhive03.Model.DAO.User;
import com.devhive03.Repository.UserDAORepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAORepository userDAORepository;

    @Transactional
    public User 회원찾기(String username) {
        return userDAORepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public User 회원가입(User user){
        return userDAORepository.save(user);

    }
    public ResponseEntity<String> addRating(Long userID, Long Rating) {
        return userDAORepository.findById(userID)
                .map(user -> {
                    Long userRating = user.getRating();
                    Long RatingUserCnt = user.getRatingUserCnt();
                    user.setRating(((userRating * RatingUserCnt) + Rating) / (RatingUserCnt + 1));
                    user.setRatingUserCnt(RatingUserCnt + 1);

                    userDAORepository.save(user);
                    return ResponseEntity.ok("{\"message\":\"success\"}");
                })
                .orElse(ResponseEntity.badRequest().body("{\"message\":\"User not found\"}"));
    }

}