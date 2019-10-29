package com.lunatech.service;

import com.lunatech.dao.PrincipalRepository;
import com.lunatech.dao.TitleRepository;
import com.lunatech.dto.TitleDto;
import com.lunatech.util.mapper.TitleMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Services related to Titles (Movies, tv shows, etc)
 */
@Service
public class TitleService {
    private final TitleMapper titleMapper = Mappers.getMapper(TitleMapper.class);

    @Value("${title.max_count_fetched_by_name_or_original_name}")
    private int maxCountFetched;
    @Value("${title.max_count_fetched_by_top_rating_and_genre}")
    private int maxCountTopRating;

    private final TitleRepository titleRepository;
    private final PrincipalRepository principalRepository;

    @Autowired
    public TitleService(TitleRepository titleRepository, PrincipalRepository principalRepository) {
        this.titleRepository = titleRepository;
        this.principalRepository = principalRepository;
    }


    /**
     * @param title primary or original name of movie/tv show, etc
     * @return list of {@code TitleDto} containing information about the titles
     */
    public List<TitleDto> findTitleList(String title) {
        return titleMapper.fromTitle(
                titleRepository.findByPrimaryTitleOrOriginalTitle(title, title,
                        PageRequest.of(0, maxCountFetched, Sort.Direction.DESC, "numberOfVote", "averageRating"))
        );
    }

    /**
     * find a title (movie/tv show, etc ) by Id
     *
     * @param titleId id of title (movie/tv show. etc)
     * @return {@code TitleDto} containing information about the title
     */
    public TitleDto findById(String titleId) {
        return titleMapper.fromTitle(titleRepository.findById(titleId).orElse(null));
    }

    /**
     * return top rated movies of a specific genre that have at least {@code minNumberOfVote} votes from users
     *
     * @param genre           genre name
     * @param minNumberOfVote minimum number of votes for the movie
     * @return list of {@code TitleDto}
     */
    public List<TitleDto> findTopRatingByGenre(String genre, Integer minNumberOfVote) {
        return titleMapper.fromTitle(
                titleRepository.findTopRatingByGenre(genre, minNumberOfVote,
                        PageRequest.of(0, maxCountTopRating, Sort.Direction.DESC, "averageRating"))
        );
    }


    /**
     * find all movie list that all people have shared
     *
     * @param nameIdList list of name id's of people
     * @return list of {@code TitleDto} common between names
     */
    public List<TitleDto> findCoincidence(List<String> nameIdList) {

        if (nameIdList.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> commonTitleIdList = null;

        for (String nameId : nameIdList) {
            if (commonTitleIdList == null) {
                commonTitleIdList = principalRepository.findTitleIdByName(nameId);
            } else {
                commonTitleIdList = principalRepository.findCommonTitleId(nameId, commonTitleIdList);
            }

            if (commonTitleIdList.isEmpty())
                return new ArrayList<>();
        }

        return titleMapper.fromTitle(titleRepository.findAllById(commonTitleIdList));
    }


}
