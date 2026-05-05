package com.example.star_wars_api.services;

import com.example.star_wars_api.services.clients.PeopleClientModel;
import com.example.star_wars_api.services.clients.StarWarsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarWarsService {

    @Autowired
    private final StarWarsClient starWarsClient;

    public StarWarsService(StarWarsClient starWarsClient) {
        this.starWarsClient = starWarsClient;
    }

    public PeopleModel processPeople(String id){

        return mapClientToService(starWarsClient.getPeople(id));
    }

    private static PeopleModel mapClientToService(PeopleClientModel peopleClientModel) {
        return new PeopleModel(peopleClientModel.name());
    }
}
