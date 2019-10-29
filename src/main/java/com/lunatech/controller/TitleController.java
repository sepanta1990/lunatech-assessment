package com.lunatech.controller;

import com.lunatech.dto.TitleDto;
import com.lunatech.service.TitleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/title")
@Api(value = "title", description = "Operations pertaining to titles: movies, tv shows, etc")
public class TitleController {

    private final TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @ApiOperation(value = "Retrieves a list of titles that their primary or original name match the search criteria")
    @GetMapping("/search")
    public ResponseEntity<List<TitleDto>> titleByPrimaryOrOriginalName(@RequestParam String primaryOrOriginalName) {
        return ResponseEntity.status(HttpStatus.OK).body(titleService.findTitleList(primaryOrOriginalName));

    }

    @ApiOperation(value = "Search a title with an ID", response = TitleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the title"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<TitleDto> titleById(@PathVariable("id") String id) {
        return Optional.ofNullable(titleService.findById(id)).map(ResponseEntity.status(HttpStatus.OK)::body)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ApiOperation(value = "Retrieves a list of top rated titles  for a genre and minimum number of received votes ")
    @GetMapping("/top-rated")
    public ResponseEntity<List<TitleDto>> topTitleByGenre(@RequestParam String genre, @RequestParam Integer minNumberOfVote) {
        return ResponseEntity.status(HttpStatus.OK).body(titleService.findTopRatingByGenre(genre, minNumberOfVote));
    }

    @ApiOperation(value = "Retrieves a list of titles that all people have shared")
    @GetMapping("/find-coincidence")
    public ResponseEntity<List<TitleDto>> findCoincidence(@RequestParam List<String> nameIdList) {
        return ResponseEntity.status(HttpStatus.OK).body(titleService.findCoincidence(nameIdList));
    }
}
