package com.devhive03.Controller.api;

import com.devhive03.Repository.UserDAORepository;
import com.devhive03.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 평점", description = "유저 평점 API")
@RestController
public class UserRatingController {
    @Autowired
    private UserService userService;

    @Operation(summary = "유저 평가하기")
    @GetMapping("/userratings/add")
    public ResponseEntity<String> AddUserRating(@Parameter Long userID, @Parameter Long rating){
        return userService.addRating(userID, rating);
    }

}
