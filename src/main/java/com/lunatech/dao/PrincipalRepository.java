package com.lunatech.dao;

import com.lunatech.model.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PrincipalRepository extends JpaRepository<Principal, Integer> {

    @Query("select p.title.id from Principal p where p.name.id= :name and  p.title.id in :titleIdList")
    public List<String> findCommonTitleId(@Param("name") String name, @Param("titleIdList") List<String> titleIdList);

    @Query("select p.title.id from Principal p where p.name.id = ?1")
    List<String> findTitleIdByName(String nameId);

    @Query("select p.name.id from Principal p where p.title.id in (select p.title.id from Principal p where p.name.id = :nameId) and not p.name.id in (:visitedNames)")
    List<String> findFirstDegreeNames(@Param("nameId") String nameId, @Param("visitedNames") Set<String> visitedNames);
}
