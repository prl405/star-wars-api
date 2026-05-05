package com.example.star_wars_api.services.clients;


import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PeopleClient implements StarWarsClient {

    private final WebClient webClient;

    public PeopleClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public PeopleClientModel getPeople(String id) {

        return webClient.get()
                .uri("/people/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                Mono.error(new RuntimeException("Client error: " + response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Server error: " + response.statusCode()))
                )
                .bodyToMono(PeopleClientModel.class)
                .block();
    }
}
