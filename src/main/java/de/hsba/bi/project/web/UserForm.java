package de.hsba.bi.project.web;

import de.hsba.bi.project.user.Role;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames ={"user"}))
public class UserForm {

    // ermöglicht Fehlermeldungen, wenn Angaben zu neuem User nicht vollständig sind

    @NotEmpty(message = "Bitte einen Namen eingeben")
    @Getter
    @Setter
    private String name;

    @NotEmpty(message = "Bitte ein Passwort festlegen")
    @Getter
    @Setter
    private String password;

    @NotNull(message = "Bitte einen Rolle eingeben")
    @Getter
    @Setter
    private Role role;
}