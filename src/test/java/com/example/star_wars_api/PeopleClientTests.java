package com.example.star_wars_api;

import com.example.star_wars_api.services.clients.PeopleClient;
import com.example.star_wars_api.services.clients.PeopleClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeopleClientTests {

    @Mock
    private WebClient webClient;

    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private PeopleClient peopleClient;

    @BeforeEach
    void setup() {
        peopleClient = new PeopleClient(webClient);
    }

    @Test
    void getPeople_shouldReturnPerson() {
        var expected = new PeopleClientModel("Luke Skywalker");

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/people/{id}", "1")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(Mockito.any(), Mockito.any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PeopleClientModel.class)).thenReturn(Mono.just(expected));

        var result = peopleClient.getPeople("1");
        assertEquals("Luke Skywalker", result.name());
    }

    @Test
    void getPeople_shouldThrowExceptionOnClientError() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/people/{id}", "1")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(Mockito.any(), Mockito.any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PeopleClientModel.class)).thenReturn(Mono.error(new RuntimeException("Client error: 404")));

        assertThrows(RuntimeException.class, () -> peopleClient.getPeople("1"));
    }
}