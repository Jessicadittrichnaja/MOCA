package de.hsba.bi.project.web;

import de.hsba.bi.project.roles.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

//@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames ={"user"}))
public class UserForm {

    // ermöglicht Fehlermeldungen, wenn Angaben zu neuem User nicht vollständig sind

    @Getter
    @Setter
    @NotBlank(message = "Der Vorname darf nicht leer sein")
    private String firstName;

    @Getter
    @Setter
    @NotBlank(message = "Der Nachname darf nicht leer sein")
    private String lastName;

    @Getter
    @Setter
    @NotBlank(message = "Der Nutzername darf nicht leer sein")
    private String userName;

    @Getter
    @Setter
    @NotBlank(message = "Das Passwort darf nicht leer sein")
    private String password;

    @NotEmpty(message = "Bitte einen Rolle eingeben")
    @Getter
    @Setter
    private Set<Role> roles;

}