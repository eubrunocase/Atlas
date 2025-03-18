package com.example.Atlas.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity(name = "professor")
@EqualsAndHashCode(of = "id")
public class Professor extends Users{

    public Professor() {

    }

    @Override
    public String toString() {
        return "Professor{}";
    }
}
