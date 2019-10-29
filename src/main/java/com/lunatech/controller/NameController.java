package com.lunatech.controller;

import com.lunatech.dto.TypeCastedResponse;
import com.lunatech.service.NameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name")
@Api(value = "name", description = "Operations pertaining to names: actor actress, writer, director, etc")
public class NameController {

    private final NameService nameService;

    @Autowired
    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @ApiOperation(value = "Determines if that person has become type casted (at least half of their work is one genre)", response = TypeCastedResponse.class)
    @GetMapping("/is-type-casted/{personNameId}")
    public ResponseEntity<TypeCastedResponse> isTypeCasted(@PathVariable("personNameId") String nameId) {
        return nameService.isTypeCasted(nameId).map(ResponseEntity.ok()::body).orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Determines the degree of separation between two people)", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the separation degree between two people"),
            @ApiResponse(code = 404, message = "You have entered an invalid id for first or second person. Or there is no link between two people")
    })
    @GetMapping("find-separation-degree/from/{firstPersonId}/to/{secondPersonId}")
    public ResponseEntity<Integer> findSeparationDegree(@PathVariable("firstPersonId") String firstPersonId, @PathVariable("secondPersonId") String secondPersonId) {
        return nameService.findSeparationDegree(firstPersonId, secondPersonId).map(ResponseEntity.ok()::body).orElse(ResponseEntity.notFound().build());
    }
}
