package com.devhive03.Repository;

import com.devhive03.Model.DAO.SearchList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchListDAORepository extends JpaRepository<SearchList, String> {
    List<SearchList> findAllByUser_IdOrderBySearchDateDesc(Long userId);
}
