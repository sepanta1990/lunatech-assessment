package com.lunatech.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
@Data
public class Genre {
    @Id
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genreList")
    private List<Title> titleList;
}
