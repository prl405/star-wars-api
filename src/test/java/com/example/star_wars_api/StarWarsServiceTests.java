package com.example.star_wars_api;


import com.example.star_wars_api.services.StarWarsService;
import com.example.star_wars_api.services.clients.PeopleClientModel;
import com.example.star_wars_api.services.clients.StarWarsClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StarWarsServiceTests {

    @Mock
    private StarWarsClient starWarsClient;

    private StarWarsService starWarsService;

    @BeforeEach
    void setup() {
        starWarsService = new StarWarsService(starWarsClient);
    }

    @Test
    void processPeople_shouldReturnName() {
        var person = new PeopleClientModel("Luke");
        when(starWarsClient.getPeople("1")).thenReturn(person);
        assertEquals("Luke", starWarsService.processPeople("1").name());
    }
}
