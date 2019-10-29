package com.lunatech.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TitleDto {
    private String id;
    private String primaryTitle;
    private String originalTitle;
    private Boolean isAdult;
    private Short startYear;
    private Short endYear;
    private Short runtimeMinutes;
    private String averageRating;
    private Integer numberOfVote;

    private List<PrincipalDto> principalList;
    private List<String> genreList;
    private String titleType;
    private List<TitleAkaDto> titleAkaList;
    private List<NameDto> directorList;
    private List<NameDto> writerList;
    private List<EpisodeDto> episodeDtoList;
}
