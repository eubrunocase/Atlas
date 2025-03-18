package com.example.Atlas.model.DTO;

import com.example.Atlas.model.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {
}
