package com.lunatech.dao;

import com.lunatech.model.Title;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface TitleRepository extends JpaRepository<Title, String> {

    public List<Title> findByPrimaryTitleOrOriginalTitle(String primaryTitle, String originalTitle, Pageable pageable);

    @Query("select t from Title t inner join t.genreList g where g.id=?1 and t.numberOfVote >= ?2")
    public List<Title> findTopRatingByGenre(String genre, Integer minNumberOfVote, Pageable pageable);
}
