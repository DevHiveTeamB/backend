package com.devhive03.Model.DTO.Search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter @Setter
@AllArgsConstructor
public class SearchListDTO {
    private String searchData;
    private Timestamp searchDate;

    // Constructors, Getters, and Setters
}
