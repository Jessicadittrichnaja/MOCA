package de.hsba.bi.project.web;

import de.hsba.bi.project.roles.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

//@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames ={"user"}))
public class UserForm1 {

    // ermöglicht Fehlermeldungen, wenn Angaben zu neuem User nicht vollständig sind. Passwort wird nicht überprüft (wird genutzt, um Änderungen von Nutzern zu prüfen)

    @Getter
    @Setter
    @NotBlank(message = "Der Nutzername darf nicht leer sein")
    private String name;

    @NotEmpty(message = "Bitte wähle mindestens eine Rolle")
    @Getter
    @Setter
    private Set<Role> roles;

}
