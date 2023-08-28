package com.devhive03.Controller.api;

import com.devhive03.Model.DTO.Post.PostDetailDTO;
import com.devhive03.Model.DTO.Report.PostReportRequestDTO;
import com.devhive03.Model.DTO.Report.PostReportResponseDTO;
import com.devhive03.Service.FavoritesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "찜", description = "찜 API")
@RestController
@RequestMapping("/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    //userID로 찜 목록 조회
    @Operation(summary = "userID로 찜 목록 조회")
    @GetMapping("/user/get/{userId}")
    public ResponseEntity<List<PostDetailDTO>> getPostFavoritesByuserId(@Parameter Long userId) {
        return ResponseEntity.ok(favoritesService.getPostFavoritesByuserId(userId));
    }

    //찜 생성
    // Create a new Post
    @Operation(summary = "찜 생성")
    @PostMapping("/post")
    public String createFavorites(@Parameter Long userID, @Parameter Long postID) {
        return favoritesService.createFavorites(userID, postID);
    }

    //찜 삭제
    @Operation(summary = "찜 삭제")
    @DeleteMapping("/delete")
    public String deleteFavorites(@Parameter Long userID, @Parameter Long postID) {
        return favoritesService.deleteFavorites(userID, postID);
    }
}
