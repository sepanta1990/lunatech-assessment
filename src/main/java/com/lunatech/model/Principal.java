package com.lunatech.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "principal")
@Data
public class Principal {
    @Id
    private Integer id;
    @Column(name = "job")
    private String job;
    @Column(name = "characters")
    private String characters;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_id", nullable = false)
    private Name name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;
}
