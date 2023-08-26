package com.devhive03.Repository;

import com.devhive03.Model.DAO.SearchList;
import com.devhive03.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchListDAORepository extends JpaRepository<SearchList, String> {
    List<SearchList> findAllByUser_IdOrderBySearchDateDesc(Long userId);
    List<SearchList> findByUserOrderBySearchDateAsc(User user);
    Optional<SearchList> findBySearchDataAndUser(String searchData, User user);
    void deleteByUserAndSearchData(User user, String searchData);
}
