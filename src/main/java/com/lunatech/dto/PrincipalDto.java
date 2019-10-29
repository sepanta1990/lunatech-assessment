package com.lunatech.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrincipalDto {
    private String job;
    private String characters;
    private NameDto name;
}
