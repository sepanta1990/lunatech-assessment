package com.lunatech.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "title_type")
@Data
public class TitleType {
    @Id
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "titleType")
    private List<Title> titleList;
}
