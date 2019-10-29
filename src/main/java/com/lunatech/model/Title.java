package com.lunatech.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Title {
    @Id
    private String id;
    @Column(name = "primary_title")
    private String primaryTitle;
    @Column(name = "original_title")
    private String originalTitle;
    @Column(name = "is_adult")
    private Boolean isAdult;
    @Column(name = "start_year")
    private Short startYear;
    @Column(name = "end_year")
    private Short endYear;
    @Column(name = "runtime_minutes")
    private Short runtimeMinutes;
    @Column(name = "average_rating")
    private Float averageRating;
    @Column(name = "number_of_vote")
    private Integer numberOfVote;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "title")
    private List<Principal> principalList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "title_genre", joinColumns = {
            @JoinColumn(name = "title_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "genre_id",
                    nullable = false, updatable = false)})
    private List<Genre> genreList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_type", nullable = false)
    private TitleType titleType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentTitle")
    private List<TitleAka> titleAkaList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "knownForTitleList")
    private List<Name> knownByTitleList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "directedTitleList")
    private List<Name> directorList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "writtenTitleList")
    private List<Name> writerList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentTitle")
    private List<Episode> episodeList;
}
