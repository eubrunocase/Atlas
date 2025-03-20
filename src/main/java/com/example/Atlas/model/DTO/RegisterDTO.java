package com.example.Atlas.model.DTO;

import com.example.Atlas.model.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterDTO(@JsonProperty("login") String login,
                          @JsonProperty("password") String password,
                          @JsonProperty("role") UserRoles role) {
}
