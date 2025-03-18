package com.example.Atlas.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationDTO(@JsonProperty("login") String login,@JsonProperty("password") String password) {
}
