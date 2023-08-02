package com.devhive03.Service;

import com.devhive03.Model.DAO.User;
import com.devhive03.Repository.UserDAORepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAORepository UserDAORepository;

    @Transactional
    public void 회원가입(User user){
        UserDAORepository.save(user);

    }
}
