package com.example.Atlas.model;

import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "professor")
@EqualsAndHashCode(of = "id")
public class Professor extends Users{

    @Override
    public String toString() {
        return "Professor{}";
    }
}
