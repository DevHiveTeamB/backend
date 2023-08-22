package com.devhive03.Model.DTO.Search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class UserSearchResponse {
    private Long userID;
    private List<SearchListDTO> searchList;

    // Constructors, Getters, and Setters
}