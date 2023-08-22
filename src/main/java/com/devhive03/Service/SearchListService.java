package com.devhive03.Service;

import com.devhive03.Model.DAO.SearchList;
import com.devhive03.Repository.SearchListDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchListService {

    @Autowired
    private SearchListDAORepository searchListDAORepository;

    public List<SearchList> findAllByUserId(Long userId) {
        return searchListDAORepository.findAllByUser_IdOrderBySearchDateDesc(userId);
    }

    public void saveSearchRecord(SearchList searchList) {
        searchListDAORepository.save(searchList);
    }

    public void deleteBySearchData(String searchData) {
        searchListDAORepository.deleteById(searchData);
    }
}
