package com.example.Atlas.model;


import com.example.Atlas.model.roles.UserRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED) //definindo estratégia de herança no banco de dados
@Entity(name = "users")
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public abstract class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long id;

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    public void setId (long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
