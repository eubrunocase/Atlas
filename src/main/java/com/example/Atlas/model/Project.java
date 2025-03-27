package com.example.Atlas.model;

import com.example.Atlas.model.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    @JsonProperty("id")
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
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

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
