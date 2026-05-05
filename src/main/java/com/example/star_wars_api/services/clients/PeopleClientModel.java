package com.example.star_wars_api.services.clients;

import jakarta.validation.constraints.Size;
import lombok.*;


public record PeopleClientModel(
        @NonNull@Size(max = 50) String name
) {}
