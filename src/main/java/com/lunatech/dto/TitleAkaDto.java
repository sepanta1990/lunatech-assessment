package com.lunatech.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TitleAkaDto {
    private Integer id;
    private String title;
    private String region;
    private String language;
    private String attributes;
    private Boolean isOriginalTitle;
}
