package com.devhive03;

import com.devhive03.Model.DAO.User;
import com.devhive03.Repository.UserDAORepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestConfig {
    @Autowired
    private UserDAORepository userDAORepository;

    @Test
    public void testuserConfig(){
        User user = new User();
        user.setUsername("람운");
        userDAORepository.save(user);
    }
}
