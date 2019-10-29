package com.lunatech.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameDto {
    private String id;
    private String primaryName;
    private Short birthYear;
    private Short deathYear;
    private String primaryProfession;
}
