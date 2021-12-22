package de.hsba.bi.project.web;

import de.hsba.bi.project.user.Role;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

//@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames ={"user"}))
public class UserForm {

    // ermöglicht Fehlermeldungen, wenn Angaben zu neuem User nicht vollständig sind

    @NotEmpty(message = "Bitte einen Namen eingeben")
    @Getter
    @Setter
    @NotBlank(message = "Der Nutzername darf nicht leer sein")
    private String name;

    @NotEmpty(message = "Bitte ein Passwort festlegen")
    @Getter
    @Setter
    @NotBlank(message = "Das Passwort darf nicht leer sein")
    private String password;

    @NotNull(message = "Bitte einen Rolle eingeben")
    @Getter
    @Setter
//    @NotEmpty(message = "Es muss eine Nutzerrolle festgelegt werden")
    private Role role;
}