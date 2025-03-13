package com.example.Atlas.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "administrador")
@EqualsAndHashCode(of = "id")
public class Administrador extends Users{

    @Override
    public String toString() {
        return "Administrador{}";
    }
}
