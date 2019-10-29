package com.lunatech.service;

import com.lunatech.dao.GenreRepository;
import com.lunatech.dao.NameRepository;
import com.lunatech.dao.PrincipalRepository;
import com.lunatech.dao.bean.GenreCountBean;
import com.lunatech.dto.TypeCastedResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Services related to Names (actors, actresses, directors, writers, etc)
 */
@Service
public class NameService {
    private final GenreRepository genreRepository;
    private final NameRepository nameRepository;
    private final PrincipalRepository principalRepository;

    @Autowired
    public NameService(GenreRepository genreRepository, NameRepository nameRepository, PrincipalRepository principalRepository) {
        this.genreRepository = genreRepository;
        this.nameRepository = nameRepository;
        this.principalRepository = principalRepository;
    }

    /**
     * checks whether a person is type casted
     * by checking the number of movies from a particular genre
     * and comparing with total number of his/her movies.
     * if at least half of his/her works is from a specific genre he/she is type casted.
     *
     * @param nameId name id of a person
     * @return an Optional of {code {@link TypeCastedResponse}}  if the person is type casted or {@code {@link TypeCastedResponse}} otherwise
     */
    public Optional<TypeCastedResponse> isTypeCasted(String nameId) {

        return nameRepository.findById(nameId).flatMap(name -> {
            long totalWorksCount = name.getPrincipalList().size();

            for (GenreCountBean genreCountBean : genreRepository.findAllGenreCountByNameId(nameId)) {
                if (genreCountBean.getCount() * 2 >= totalWorksCount) {
                    return Optional.of(new TypeCastedResponse(true));
                }

            }
            return Optional.of(new TypeCastedResponse(false));
        });

    }

    /**
     * searches for separation degree between two people using
     * Breadth-first search (BFS) algorithm.
     * For each person traverses the direct relations and so on respectively.
     *
     * @param sourceNameId      name id of a person
     * @param destinationNameId name id of a person
     * @return an Optional describing the separation degree between two people or empty optional if link between them not found or input id's are invalid
     */
    public Optional<Integer> findSeparationDegree(String sourceNameId, String destinationNameId) {

        if (!nameRepository.findById(sourceNameId).isPresent() || !nameRepository.findById(destinationNameId).isPresent())
            return Optional.empty();
        if (sourceNameId.equals(destinationNameId))
            return Optional.of(0);

        Set<String> visitedNodes = new HashSet<>();
        LinkedList<BfsSearchBean> bfsQueue = new LinkedList<>();

        bfsQueue.addLast(new BfsSearchBean(sourceNameId, 0));
        visitedNodes.add(sourceNameId);


        while (!bfsQueue.isEmpty()) {
            BfsSearchBean name = bfsQueue.removeFirst();

            List<String> firstDegreeNames = principalRepository.findFirstDegreeNames(name.getId(), visitedNodes);
            for (String firstDegreeNameId : firstDegreeNames) {
                visitedNodes.add(firstDegreeNameId);
                bfsQueue.addLast(new BfsSearchBean(firstDegreeNameId, name.getLevel() + 1));
                if (destinationNameId.equals(firstDegreeNameId))
                    return Optional.of(bfsQueue.getLast().level);
            }
        }

        return Optional.empty();
    }

    /***
     *used to store level of separation for an specific person when traversing relationships using BFS algorithm
     */
    @Data
    private class BfsSearchBean {
        private final String id;
        private final int level;

    }
}
