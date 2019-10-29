package com.lunatech.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "title_aka")
@Data
public class TitleAka {
    @Id
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "region")
    private String region;
    @Column(name = "language")
    private String language;
    private String attributes;
    @Column(name = "is_original_title")
    private Boolean isOriginalTitle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id", nullable = false)
    private Title parentTitle;
}
