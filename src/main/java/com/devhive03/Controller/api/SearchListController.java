package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.SearchList;
import com.devhive03.Model.DTO.Search.SearchListDTO;
import com.devhive03.Model.DTO.Search.UserSearchResponse;
import com.devhive03.Service.SearchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/searchlist")
public class SearchListController {

    @Autowired
    private SearchListService searchListService;

    @GetMapping("/user/get/{userID}")
    public UserSearchResponse getUserSearchList(@PathVariable Long userID) {
        List<SearchList> searchLists = searchListService.findAllByUserId(userID);
        List<SearchListDTO> searchListDTOs = searchLists.stream()
                .map(searchList -> new SearchListDTO(searchList.getSearchData(), searchList.getSearchDate()))
                .collect(Collectors.toList());

        return new UserSearchResponse(userID, searchListDTOs);
    }

    @PostMapping("/post")
    public String createSearchRecord(@RequestBody SearchList searchList) {
        searchListService.saveSearchRecord(searchList);
        return "{\"message\":\"success\"}";
    }

    @DeleteMapping("/delete/{searchData}")
    public String deleteSearchRecord(@PathVariable String searchData) {
        searchListService.deleteBySearchData(searchData);
        return "{\"message\":\"success\"}";
    }
}