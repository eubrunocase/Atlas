package com.example.Atlas.model;

import com.example.Atlas.model.enums.UserRoles;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity(name = "administrador")
@EqualsAndHashCode(of = "id")
public class Administrador extends Users{

    public Administrador (String login, String password, UserRoles role) {
        super(login, password, role);
    }

    public Administrador() {
        super("", "", UserRoles.ADMINISTRADOR);
    }

    @Override
    public String toString() {
        return "Administrador{}";
    }


}
