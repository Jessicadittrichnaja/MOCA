package de.hsba.bi.project.web;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames ={"user"}))
public class UserForm {

    // ermöglicht Fehlermeldungen, wenn Angaben zu neuem User nicht vollständig sind

    @NotEmpty(message = "Bitte einen Namen eingeben")
    @Getter
    @Column(name = "user")
    @Setter
    private String name;

    @NotEmpty(message = "Bitte ein Passwort festlegen")
    @Getter
    @Setter
    private String password;

    @NotEmpty(message = "Bitte einen Rolle eingeben")
    @Getter
    @Setter
    private String role;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}