package com.example.star_wars_api;

import com.example.star_wars_api.controllers.StarWarsController;
import com.example.star_wars_api.services.PeopleModel;
import com.example.star_wars_api.services.StarWarsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StarWarsController.class)
public class StarWarsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private StarWarsService starWarsService;

    @Test
    public void hello_shouldReturnHelloWorld() throws Exception {
        mvc.perform(get("/api/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world!"));
    }

    @Test
    public void retrievePerson_shouldReturnCharacterName() throws Exception {
        var expected = new PeopleModel("Luke Skywalker");

        when(starWarsService.processPeople("1")).thenReturn(expected);

        mvc.perform(get("/api/character_name/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Luke Skywalker"));
    }
}
