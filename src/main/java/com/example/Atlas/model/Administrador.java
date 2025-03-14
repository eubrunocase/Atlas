package com.example.Atlas.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity(name = "administrador")
@EqualsAndHashCode(of = "id")
public class Administrador extends Users{

    public Administrador() {
    }

    @Override
    public String toString() {
        return "Administrador{}";
    }
}
