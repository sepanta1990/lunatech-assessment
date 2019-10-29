package com.lunatech.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Episode {
    @Id
    private String id;
    @Column(name = "season_number")
    private Integer seasonNumber;
    @Column(name = "episode_number")
    private Integer episodeNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private Title parentTitle;
}
