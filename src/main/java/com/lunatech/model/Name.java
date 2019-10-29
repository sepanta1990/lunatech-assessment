package com.lunatech.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "name")
@Data
public class Name {
    @Id
    private String id;
    @Column(name = "primary_name")
    private String primaryName;
    @Column(name = "birth_year")
    private Short birthYear;
    @Column(name = "death_year")
    private Short deathYear;
    @Column(name = "primary_profession")
    private String primaryProfession;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "known_for_title", joinColumns = {
            @JoinColumn(name = "name_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "title_id",
                    nullable = false, updatable = false)})
    private List<Title> knownForTitleList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "title_director", joinColumns = {
            @JoinColumn(name = "director_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "title_id",
                    nullable = false, updatable = false)})
    private List<Title> directedTitleList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "title_writer", joinColumns = {
            @JoinColumn(name = "writer_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "title_id",
                    nullable = false, updatable = false)})
    private List<Title> writtenTitleList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "name")
    private List<Principal> principalList;
}
