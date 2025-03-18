package com.example.Atlas.model;

import com.example.Atlas.model.enums.UserRoles;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity(name = "professor")
@EqualsAndHashCode(of = "id")
public class Professor extends Users{

    public Professor(String login, String password, UserRoles role) {
       super(login, password, role);
    }

    public Professor() {
        super("", "", UserRoles.PROFESSOR);
    }

    @Override
    public String toString() {
        return "Professor{}";
    }

}
