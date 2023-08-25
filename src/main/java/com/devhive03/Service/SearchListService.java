package com.devhive03.Service;

import com.devhive03.Model.DAO.SearchList;
import com.devhive03.Model.DAO.User;
import com.devhive03.Repository.SearchListDAORepository;
import com.devhive03.Repository.UserDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SearchListService {

    @Autowired
    private SearchListDAORepository searchListDAORepository;
    @Autowired
    private UserDAORepository userDAORepository;

    public List<SearchList> findAllByUserId(Long userId) {
        return searchListDAORepository.findAllByUser_IdOrderBySearchDateDesc(userId);
    }

    public void saveSearchRecord(String searchData, Long userId) {
        Optional<User> userOptional = userDAORepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 동일한 검색어가 이미 저장되어 있는지 확인
            Optional<SearchList> existingSearch = searchListDAORepository.findBySearchDataAndUser(searchData, user);

            // 만약 동일한 검색어가 이미 저장되어 있다면 아무런 작업도 수행하지 않음
            if (existingSearch.isPresent()) {
                return;
            }

            // 유저가 가진 SearchList 레코드의 개수를 확인
            List<SearchList> userSearchLists = searchListDAORepository.findByUserOrderBySearchDateAsc(user);

            // 만약 6개 이상인 경우 가장 오래된 레코드(첫번째 레코드)를 삭제
            if (userSearchLists.size() >= 6) {
                searchListDAORepository.delete(userSearchLists.get(0));
            }

            SearchList searchList = new SearchList();
            searchList.setSearchData(searchData);
            searchList.setUser(user);
            searchListDAORepository.save(searchList);
        }
    }


    public void deleteBySearchData(String searchData) {
        searchListDAORepository.deleteById(searchData);
    }
}
