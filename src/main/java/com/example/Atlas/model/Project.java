package com.example.Atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "project")
@EqualsAndHashCode(of = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("objetivo")
    private String objetivo;

    @JsonProperty("data")
    private String dataInicio;

    @JsonProperty("escopo")
    private String escopo;

    @JsonProperty("publico-alvo")
    private String publicoAlvo;

    @JsonProperty("status")
    private String status;

    @Override
    public String toString() {
        return "Project{" +
                "dataInicio='" + dataInicio + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", escopo='" + escopo + '\'' +
                ", publicoAlvo='" + publicoAlvo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
