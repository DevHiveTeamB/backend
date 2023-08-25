package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.SearchList;
import com.devhive03.Model.DTO.Search.SearchListDTO;
import com.devhive03.Model.DTO.Search.UserSearchResponse;
import com.devhive03.Service.SearchListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "최근 검색", description = "최근 검색 API")
@RestController
@RequestMapping("/searchlist")
public class SearchListController {

    @Autowired
    private SearchListService searchListService;

    @Operation(summary = "유저의 최근검색을 시간으로 정렬해서 조회")
    @GetMapping("/user/get/{userID}")
    public UserSearchResponse getUserSearchList(@PathVariable Long userID) {
        List<SearchList> searchLists = searchListService.findAllByUserId(userID);
        List<SearchListDTO> searchListDTOs = searchLists.stream()
                .map(searchList -> new SearchListDTO(searchList.getSearchData(), searchList.getSearchDate()))
                .collect(Collectors.toList());

        return new UserSearchResponse(userID, searchListDTOs);
    }

    @Operation(summary = "최근 검색 저장, 같은 검색어면 저장안하고 한 유저가 7개 이상 최근검색을 저장하면 오래된 검색 자동 삭제")
    @PostMapping("/post")
    public String createSearchRecord(@RequestBody String search, @Parameter Long userId) {
        searchListService.saveSearchRecord(search, userId);
        return "{\"message\":\"success\"}";
    }
}