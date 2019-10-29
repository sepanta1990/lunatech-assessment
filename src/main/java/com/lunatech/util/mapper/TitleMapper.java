package com.lunatech.util.mapper;

import com.lunatech.dto.TitleDto;
import com.lunatech.model.Genre;
import com.lunatech.model.Title;
import com.lunatech.model.TitleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper object converts a title object to titleDto
 */
@Mapper
public abstract class TitleMapper {

    /**
     * coverts a title object to titleDto used in rest calls response
     *
     * @param titleList list of titles
     * @return list of {@code TitleDto}
     */
    public abstract List<TitleDto> fromTitle(List<Title> titleList);

    @Mappings({@Mapping(target = "genreList", source = "genreList"),
            @Mapping(target = "averageRating", source = "averageRating", numberFormat = "##.0")})
    public abstract TitleDto fromTitle(Title title);

    public abstract List<String> fromGenre(List<Genre> genreList);

    /**
     * returns name of {@code Genre}  object when converting to Dto
     *
     * @param genre genre to be converted
     * @return genre name
     */
    String fromGenre(Genre genre) {
        return genre == null ? null : genre.getName();
    }

    /**
     * returns name of {@code Title}  object when converting to Dto
     *
     * @param titleType title type to be covnerted
     * @return title type name
     */
    String fromTitleType(TitleType titleType) {
        return titleType == null ? null : titleType.getName();
    }

}
