package com.devhive03.Controller.api;

import com.devhive03.Service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "거래완료")
@RestController
@RequestMapping("/transaction")
public class TransactionCompletedController {
    @Autowired
    private PostService postService;

    // Create a new Post
    @Operation(summary = "거래 완료")
    @PostMapping("/post")
    public String TransactionCompleted(@Parameter Long postId) {
        return postService.TransactionCompleted(postId);
    }
}
