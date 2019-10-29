package com.lunatech.dao;

import com.lunatech.dao.bean.GenreCountBean;
import com.lunatech.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, String> {

    @Query("select new com.lunatech.dao.bean.GenreCountBean(count(g.name),g.name) from Genre g inner join g.titleList t inner join t.principalList p where p.name.id=?1 group by g.name")
    public List<GenreCountBean> findAllGenreCountByNameId(String nameId);
}
