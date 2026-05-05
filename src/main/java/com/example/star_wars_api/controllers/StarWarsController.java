package com.example.star_wars_api.controllers;

import com.example.star_wars_api.services.PeopleModel;
import com.example.star_wars_api.services.StarWarsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Star Wars", description = "Star Wars character endpoints")
public class StarWarsController {

    private final StarWarsService starWarsService;

    public StarWarsController(StarWarsService starWarsService) {
        this.starWarsService = starWarsService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @Operation(summary = "Get a character by ID", description = "Fetches a Star Wars character from SWAPI")
    @GetMapping("/character_name/{id}")
    public PeopleResponseModel retrievePerson(@PathVariable String id){
        return mapServiceToController(starWarsService.processPeople(id));
    }

    private static PeopleResponseModel mapServiceToController(PeopleModel peopleModel) {
        return new PeopleResponseModel(peopleModel.name());
    }
}
