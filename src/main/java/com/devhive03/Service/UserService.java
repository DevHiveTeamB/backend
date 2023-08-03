package com.devhive03.Service;

import com.devhive03.Model.DAO.User;
import com.devhive03.Repository.UserDAORepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAORepository UserDAORepository;

    @Transactional
    public User 회원찾기(String username) {
        return UserDAORepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public User 회원가입(User user){
        return UserDAORepository.save(user);

    }
}